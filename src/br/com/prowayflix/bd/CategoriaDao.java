package br.com.prowayflix.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.prowayflix.interfaces.ICadastro;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Filme;
import br.com.prowayflix.model.Perfil;

public class CategoriaDao extends Banco implements ICadastro {

	private final String TABELA = "Categoria";

	@Override
	public Object Create(Object novo) {
		Categoria item = (Categoria) novo;
		if (Find(item.getNome()) != null) {
			System.out.println("Erro ao adicionar. J� existe registro com nome " + item.getNome());
		} else {
			if(Executar(item, "INSERT INTO " + TABELA + " (nome) VALUES (?)")) {
				item = (Categoria) Find(item.getNome());
				item.ExibirDetalhes();
				System.out.println("Salvo com sucesso!");
			}   
		}
		return item;
	}

	@Override
	public Object ReadAll(Object pai) {
		ResultSet resultSet = Repositorio.ConsultarBD("SELECT * from " + TABELA);

		ArrayList<Categoria> lista = new ArrayList<>();
		try {
			while (resultSet.next()) {
				if (resultSet.isFirst()) {
					System.out.println("\n\n====== Listar " + TABELA + " ========");
				}
				Categoria item = (Categoria) Preencher(resultSet);
				lista.add(item);
				item.ExibirDetalhes();

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (lista.size() == 0) {
			System.out.println("N�o h� itens para exibir");
		} else {
			System.out.println("====== Itens:" + lista.size() + " ========\n\n");
		}
		return lista;
	}

	@Override
	public Object Update(Object atual, Object editado) {
		Categoria original = (Categoria) atual;
		Categoria atualizado = (Categoria) editado;
		original = (Categoria) Find(original.getNome());
		if (original == null) {
			System.out.println("Erro ao editar. Nada foi encontrado com nome " + original.getNome());
		} else if (Find(atualizado.getNome()) != null) {
			System.out.println("Erro ao editar. J� existe um registro com nome " + atualizado.getNome());
		} else {
			System.out.println("====== EDITAR ========");
			System.out.println("====== Antes ========");
			original.ExibirDetalhes();
			System.out.println("====== Depois ========");
			
			if(Executar(atualizado, "UPDATE " + TABELA + " set nome=? "+ " WHERE Nome='" + original.getNome() + "'")) {
				original = (Categoria) Find(atualizado.getNome());
				original.ExibirDetalhes();
				System.out.println("Atualizado com sucesso!");
			}    
		}
		return original;
	}

	@Override
	public boolean Delete(Object item) {
		Categoria deletado = (Categoria) item;
		Categoria busca = (Categoria) Find(deletado.getNome());
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
		String nome = (String) chave;
		Categoria item = null;
		ResultSet resultSet = Repositorio.ConsultarBD("SELECT * from " + TABELA + " WHERE Nome='" + nome + "'");

		try {
			while (resultSet.next()) {
				item = (Categoria) Preencher(resultSet);
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
		Categoria item = new Categoria();
		try {
			item.setIdCategoria(resultSet.getInt(resultSet.findColumn("idcategoria")));
			item.setNome(resultSet.getString(resultSet.findColumn("nome")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean Executar(Object objeto, String comando) {
		Categoria item = (Categoria) objeto;
		try {
			PreparedStatement preparedStmt;
			preparedStmt = Repositorio.con.prepareStatement(comando);
			preparedStmt.setString(1, item.getNome()); 
			Repositorio.Executar(preparedStmt); 
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
