package br.com.prowayflix.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.prowayflix.interfaces.ICadastro;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Perfil;

public class PerfilDao extends Banco implements ICadastro {

	private final String TABELA = "Perfil";

	@Override
	public Object Create(Object novo) {
		Perfil item = (Perfil) novo;
		if (Find(item.getNome()) != null) {
			System.out.println("Erro ao adicionar. Já existe registro com nome " + item.getNome());
		} else {
			Repositorio.ExecutarComandoBD("INSERT INTO " + TABELA + " (nome) VALUES ('" + item.getNome() + "');");
			item = (Perfil) Find(item.getNome());
			item.ExibirDetalhes();
			System.out.println("Salvo com sucesso!");
		}
		return item;
	}

	@Override
	public Object ReadAll(Object pai) {
		ResultSet resultSet = Repositorio.ConsultarBD("SELECT * from " + TABELA);

		ArrayList<Perfil> perfis = new ArrayList<>();
		try {
			while (resultSet.next()) {
				if (resultSet.isFirst()) {
					System.out.println("\n\n====== Listar "+TABELA+" ========");
				}
				Perfil item = (Perfil) Preencher(resultSet);
				perfis.add(item);
				item.ExibirDetalhes();

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (perfis.size() == 0) {
			System.out.println("Não há itens para exibir");
		} else {
			System.out.println("====== Itens:" + perfis.size() + " ========\n\n");
		}
		return perfis;
	}

	@Override
	public Object Update(Object atual, Object editado) {
		Perfil original = (Perfil) atual;
		Perfil atualizado = (Perfil) editado;
		original = (Perfil) Find(original.getNome());
		if (original == null) {
			System.out.println("Erro ao editar. Nada foi encontrado com nome " + original.getNome());
		} else if (Find(atualizado.getNome()) != null) {
			System.out.println("Erro ao editar. Já existe um registro com nome " + atualizado.getNome());
		} else {
			System.out.println("====== EDITAR ========");
			System.out.println("====== Antes ========");
			original.ExibirDetalhes();
			System.out.println("====== Depois ========");
			Repositorio.ExecutarComandoBD("UPDATE " + TABELA + " SET nome='" + atualizado.getNome() + "'"
					+ " WHERE Nome='" + original.getNome() + "'");
			original = (Perfil) Find(atualizado.getNome());
			original.ExibirDetalhes();
			System.out.println("Atualizado com sucesso!");
		}
		return original;
	}

	@Override
	public boolean Delete(Object item) { 
		Perfil deletado = (Perfil) item;
		Perfil busca = (Perfil) Find(deletado.getNome());
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
		Perfil item = null;
		ResultSet resultSet = Repositorio.ConsultarBD("SELECT * from " + TABELA + " WHERE Nome='" + nome + "'");

		try {
			while (resultSet.next()) {
				item = (Perfil) Preencher(resultSet);
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
		Perfil item = new Perfil();
		try {
			item.setIdPerfil(resultSet.getInt(resultSet.findColumn("idperfil")));
			item.setNome(resultSet.getString(resultSet.findColumn("nome")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

}
