package br.com.prowayflix.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.prowayflix.interfaces.ICadastro;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Filme;

public class FilmeDao extends Banco implements ICadastro {

	private final String TABELA = "Filme";

	@Override
	public Object Create(Object novo) {
		Filme item = (Filme) novo;
		if (Find(item.getNome()) != null) {
			System.out.println("Erro ao adicionar. Já existe registro com nome " + item.getNome());
		} else {  
			if(Executar(item, "INSERT INTO " + TABELA + " (nome,ano,sinopse,categoriaid) VALUES (?,?,?,?)")) {
				item = (Filme) Find(Limpar(item.getNome()));
				item.ExibirDetalhes();
				System.out.println("Salvo com sucesso!");
			}  
		}
		return item;
	}

	@Override
	public Object ReadAll(Object pai) {
		ResultSet resultSet = Repositorio.ConsultarBD("SELECT f.idfilme,f.nome filme, f.ano,f.sinopse,c.idcategoria,c.nome categoria "
				+ " from " + TABELA+" f "
				+ " inner join Categoria c on c.idcategoria = f.categoriaid");

		ArrayList<Filme> lista = new ArrayList<>();
		try {
			while (resultSet.next()) {
				if (resultSet.isFirst()) {
					System.out.println("\n\n====== Listar " + TABELA + " ========");
				}
				Filme item = (Filme) Preencher(resultSet);
				lista.add(item);
				item.ExibirDetalhes();

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (lista.size() == 0) {
			System.out.println("Não há itens para exibir");
		} else {
			System.out.println("====== Itens:" + lista.size() + " ========\n\n");
		}
		return lista;
	}

	@Override
	public Object Update(Object atual, Object editado) {
		Filme original = (Filme) atual;
		Filme atualizado = (Filme) editado;
		original = (Filme) Find(original.getNome());
		if (original == null) {
			System.out.println("Erro ao editar. Nada foi encontrado com nome " + original.getNome());
		} else if (Find(atualizado.getNome()) != null) {
			System.out.println("Erro ao editar. Já existe um registro com nome " + atualizado.getNome());
		} else {
			System.out.println("====== EDITAR ========");
			System.out.println("====== Antes ========");
			original.ExibirDetalhes();
			System.out.println("====== Depois ========");
			
			if(Executar(atualizado, "UPDATE " + TABELA + " set nome=? , ano=?, sinopse=?, categoriaid=? "+ " WHERE Nome='" + original.getNome() + "'")) {
				original = (Filme) Find(atualizado.getNome());
				original.ExibirDetalhes();
				System.out.println("Atualizado com sucesso!");
			}  
			
			
		}
		return original;
	}

	@Override
	public boolean Delete(Object item) {
		Filme deletado = (Filme) item;
		Filme busca = (Filme) Find(deletado.getNome());
		if (busca != null) {
			Repositorio.ExecutarComandoBD("DELETE FROM " + TABELA + " WHERE nome='" + busca.getNome() + "'");
			System.out.println("Item deletado com sucesso!");
			return true;
		} else {
			System.out.println("Erro ao deletar. Nada foi encontrado com nome " + deletado.getNome());
			return false;
		}
	}

	@Override
	public Object Find(Object chave) {
		String nome = Limpar((String) chave);
		Filme item = null;
		ResultSet resultSet = Repositorio
				.ConsultarBD("SELECT f.idfilme,f.nome filme, f.ano,f.sinopse,c.idcategoria,c.nome categoria" + " from "
						+ TABELA + " f " + " inner join Categoria c on c.idcategoria = f.categoriaid" + " WHERE f.nome='"
						+ nome + "'");

		try {
			while (resultSet.next()) {
				item = (Filme) Preencher(resultSet);
				resultSet.isFirst();
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return item;
	}

	@Override
	public Object Preencher(ResultSet resultSet) {
		Filme item = new Filme();
		item.setCategoria(new Categoria());
		try {
			item.setIdFilme(resultSet.getInt(resultSet.findColumn("idfilme")));
			item.setNome(resultSet.getString(resultSet.findColumn("filme")));
			item.setAno(resultSet.getInt(resultSet.findColumn("ano")));
			item.setSinopse(resultSet.getString(resultSet.findColumn("sinopse")));
			item.getCategoria().setIdCategoria(resultSet.getInt(resultSet.findColumn("idcategoria")));
			item.getCategoria().setNome(resultSet.getString(resultSet.findColumn("categoria")));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean Executar(Object objeto,String comando) {

		Filme item = (Filme) objeto;
		try {
			PreparedStatement preparedStmt;
			preparedStmt = Repositorio.con.prepareStatement(comando);
			preparedStmt.setString(1, Limpar(item.getNome()));
			preparedStmt.setInt(2, item.getAno());
			preparedStmt.setString(3, Limpar(item.getSinopse()));
			preparedStmt.setInt(4, item.getCategoria().getIdCategoria()); 
			Repositorio.Executar(preparedStmt); 
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
