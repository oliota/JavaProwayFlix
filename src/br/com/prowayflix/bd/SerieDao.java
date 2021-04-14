package br.com.prowayflix.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.prowayflix.interfaces.ICadastro;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Serie;

public class SerieDao extends Banco implements ICadastro {

	private final String TABELA = "Serie";

	@Override
	public Object Create(Object novo) {
		Serie item = (Serie) novo;
		if (Find(item.getNome()) != null) {
			System.out.println("Erro ao adicionar. Já existe registro a chave " + item.getNome());
		} else {
			if (Executar(item, "INSERT INTO " + TABELA + " (nome,ano,sinopse,categoriaid) VALUES (?,?,?,?)")) {
				item = (Serie) Find(item.getNome());
				item.ExibirDetalhes();
				System.out.println("Salvo com sucesso!");
			}
		}
		return item;
	}

	@Override
	public Object ReadAll(Object pai) {
		ResultSet resultSet = Repositorio
				.ConsultarBD("SELECT s.idSerie,s.nome Serie, s.ano,s.sinopse,c.idcategoria,c.nome categoria " + " from "
						+ TABELA + " s " + " inner join Categoria c on c.idcategoria = s.categoriaid");

		ArrayList<Serie> lista = new ArrayList<>();
		try {
			while (resultSet.next()) {
				if (resultSet.isFirst()) {
					System.out.println("\n\n====== Listar " + TABELA + " ========");
				}
				Serie item = (Serie) Preencher(resultSet);
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
		Serie formulario = (Serie) atual;
		Serie atualizado = (Serie) editado;
		Serie original = (Serie) Find(formulario.getNome());
		if (original == null) {
			System.out.println("Erro ao editar. Nada foi encontrado com a chave " + formulario.getNome());
		} else if (Find(atualizado.getNome()) != null) {
			System.out.println("Erro ao editar. Já existe um registro com a chave " + atualizado.getNome());
		} else {
			System.out.println("====== EDITAR ========");

			System.out.println("====== Antes ========");
			original.ExibirDetalhes();
			System.out.println("====== Depois ========");

			if (Executar(atualizado, "UPDATE " + TABELA + " set nome=? , ano=?, sinopse=?, categoriaid=? "
					+ " WHERE lower(nome)='" + original.getNome().toLowerCase() + "'")) {
				original = (Serie) Find(atualizado.getNome());
				original.ExibirDetalhes();
				System.out.println("Atualizado com sucesso!");
			}

		}
		return original;
	}

	@Override
	public boolean Delete(Object item) {
		Serie deletado = (Serie) item;
		Serie busca = (Serie) Find(deletado.getNome());
		if (busca != null) {
			Repositorio.ExecutarComandoBD(
					"DELETE FROM " + TABELA + " WHERE lower(nome)='" + busca.getNome().toLowerCase() + "'");
			System.out.println("Item deletado com sucesso!");
			return true;
		} else {
			System.out.println("Erro ao deletar. Nada foi encontrado com a chave " + deletado.getNome());
			return false;
		}
	}

	@Override
	public Object Find(Object chave) {
		String nome = (String) chave;
		Serie item = null;
		ResultSet resultSet = Repositorio
				.ConsultarBD("SELECT s.idSerie,s.nome Serie, s.ano,s.sinopse,c.idcategoria,c.nome categoria" + " from "
						+ TABELA + " s " + " inner join Categoria c on c.idcategoria = s.categoriaid"
						+ " WHERE lower(s.nome)='" + nome.toLowerCase() + "'");

		try {
			while (resultSet.next()) {
				item = (Serie) Preencher(resultSet);
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
		Serie item = new Serie();
		item.setCategoria(new Categoria());
		try {
			item.setIdSerie(resultSet.getInt(resultSet.findColumn("idSerie")));
			item.setNome(resultSet.getString(resultSet.findColumn("Serie")));
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
	public boolean Executar(Object objeto, String comando) {

		Serie item = (Serie) objeto;
		try {
			PreparedStatement preparedStmt;
			preparedStmt = Repositorio.con.prepareStatement(comando);
			preparedStmt.setString(1, item.getNome());
			preparedStmt.setInt(2, item.getAno());
			preparedStmt.setString(3, item.getSinopse());
			preparedStmt.setInt(4, item.getCategoria().getIdCategoria());
			Repositorio.Executar(preparedStmt);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
