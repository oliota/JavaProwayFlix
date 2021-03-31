package br.com.prowayflix;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.prowayflix.bd.CategoriaDao;
import br.com.prowayflix.bd.PerfilDao;
import br.com.prowayflix.bd.Repositorio;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Perfil;

public class Main {

	public static void main(String[] args) {

		PerfilDao perfilDao = new PerfilDao();

		perfilDao.Create(new Perfil("Teste2"));
		perfilDao.ReadAll(null);
		

		Perfil perfil = (Perfil) perfilDao.Find("Teste2");
		perfil.ExibirDetalhes();
		
		perfilDao.Update(perfil, new Perfil("Teste3"));
		perfilDao.ReadAll(null);
		
		perfilDao.Delete(new Perfil("Teste22"));

		perfilDao.ReadAll(null);

		if (true)
			return;
		CategoriaDao categoriaDao = new CategoriaDao();
		categoriaDao.Create(new Categoria("Terror"));
		categoriaDao.Create(new Categoria("Drama"));
		categoriaDao.Create(new Categoria("Comédia"));
		categoriaDao.Create(new Categoria("Ação"));
		categoriaDao.Create(new Categoria("Ficção"));
		categoriaDao.ReadAll(null);

		Categoria categoria = (Categoria) categoriaDao.Find("Ação");
		categoria.ExibirDetalhes();
		categoriaDao.Update(categoria, new Categoria("Teste"));
		categoriaDao.ReadAll(null);

		categoria = (Categoria) categoriaDao.Find("Drama");
		categoria.ExibirDetalhes();
		categoriaDao.Delete(categoria);
		categoriaDao.ReadAll(null);

		// BancoDeDados();

	}

	private static void BancoDeDados() {
		Repositorio.ConectarBd("postgres");

		System.out.println("Busca simples");
		ResultSet resultSet = Repositorio.ConsultarBD("SELECT Nome from Usuario");

		try {
			while (resultSet.next()) {
				System.out.println("Nome=" + resultSet.getString(resultSet.findColumn("Nome")));

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
