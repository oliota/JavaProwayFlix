package br.com.prowayflix.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Perfil;

public class Repositorio {

	public static Connection con;
	private static String banco="prowayflix";

	public static ArrayList<Categoria> Categorias= new ArrayList<>();
	public static ArrayList<Perfil> Perfis= new ArrayList<>();
	
	static public void ConectarBd(String drive) {

		if (drive.equalsIgnoreCase("sqlserver")) {
			String connectionUrl = "jdbc:sqlserver://localhost:52718;databaseName="+banco+";";
			String login = "admin";
			String senha = "123";
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("Não há um drive JDBC adicionado no projeto");
				System.out.println("Download:");
				System.out.println(
						"https://docs.microsoft.com/pt-br/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15");
				System.out.println("Adiconar referencia ao projeto");
			}

			try {
				con = DriverManager.getConnection(connectionUrl, login, senha);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao conectar no banco de dados SQLSERVER, verifique a mensagem de erro acima");
			}
		}
		
		if (drive.equalsIgnoreCase("postgres")) {
			String url = "jdbc:postgresql://localhost/"+banco;
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			props.setProperty("password", "postgres");
			props.setProperty("ssl", "false");
			try {
				con = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	static public ResultSet ConsultarBD(String sql) {
		Statement statement;
		try {
			statement = con.createStatement();
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha ao realizar a consulta no banco de dados");
			System.out.println("Sql utilizado:");
			System.out.println(sql);
			return null;
		}
	}

	static public boolean ExecutarComandoBD(String sql) {
		Statement statement;
		try {
			statement = con.createStatement();
			statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	static public boolean Executar(PreparedStatement preparedStmt) { 
		try { 
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
