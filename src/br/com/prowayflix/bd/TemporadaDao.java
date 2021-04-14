package br.com.prowayflix.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.prowayflix.interfaces.ICadastro;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Serie;
import br.com.prowayflix.model.Temporada;

public class TemporadaDao extends Banco implements ICadastro {

	private final String TABELA = "Temporada";
	private Serie serie;
	
	public TemporadaDao() { 
	}

	public TemporadaDao(Serie serie) {
		setSerie(serie);
	}
	

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	@Override
	public Object Create(Object novo) {
		Temporada item = (Temporada) novo;
		if (Find(item.getSequencial()) != null) {
			System.out.println("Erro ao adicionar. Já existe registro com a chave " + item.getSequencial());
		} else {  
			if(Executar(item, "INSERT INTO " + TABELA + " (sequencial,serieid) VALUES (?,?)")) {
				item = (Temporada) Find(item.getSequencial());
				item.ExibirDetalhes();
				System.out.println("Salvo com sucesso!");
			}  
		}
		return item;
	}

	@Override
	public Object ReadAll(Object pai) { 
		ResultSet resultSet = Repositorio.ConsultarBD("SELECT t.idTemporada,t.sequencial "
				+ " from "+TABELA+" t "
				+ " where t.serieId="+serie.getIdSerie()
				);

		ArrayList<Temporada> lista = new ArrayList<>();
		try {
			while (resultSet.next()) {
				if (resultSet.isFirst()) {
					System.out.println("\n\n====== Listar " + TABELA + " ========");
				}
				Temporada item = (Temporada) Preencher(resultSet);
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
		Temporada formulario = (Temporada) atual;
		Temporada atualizado = (Temporada) editado;
		Temporada original = (Temporada) Find(formulario.getSequencial());
		if (original == null) {
			System.out.println("Erro ao editar. Nada foi encontrado com a chave " + formulario.getSequencial());
		} else if (Find(atualizado.getSequencial()) != null) {
			System.out.println("Erro ao editar. Já existe um registro com a chave " + atualizado.getSequencial());
		} else {
			System.out.println("====== EDITAR ========");
			System.out.println("====== Antes ========");
			original.ExibirDetalhes();
			System.out.println("====== Depois ========");
			
			if(Executar(atualizado, "UPDATE " + TABELA + " set sequencial=? "+ " WHERE serieId="+serie.getIdSerie()+" sequencial=" + original.getSequencial())) {
				original = (Temporada) Find(atualizado.getSequencial());
				original.ExibirDetalhes();
				System.out.println("Atualizado com sucesso!");
			}  
			
			
		}
		return original;
	}

	@Override
	public boolean Delete(Object item) {
		Temporada deletado = (Temporada) item;
		Temporada busca = (Temporada) Find(deletado.getSequencial());
		if (busca != null) {
			Repositorio.ExecutarComandoBD("DELETE FROM " + TABELA +  " WHERE serieId="+serie.getIdSerie()+" and sequencial=" + busca.getSequencial());
			System.out.println("Item deletado com sucesso!");
			return true;
		} else {
			System.out.println("Erro ao deletar. Nada foi encontrado com a chave " + deletado.getSequencial());
			return false;
		}
	}

	@Override
	public Object Find(Object chave) {
		Integer sequencial = (Integer) chave;
		Temporada item = null;
		
		ResultSet resultSet = Repositorio.ConsultarBD( "SELECT t.idTemporada,t.sequencial "
				+ " from "+TABELA+" t "
				+ " where t.serieId="+serie.getIdSerie()
						+ " and t.sequencial="+sequencial
				);
		

		try {
			while (resultSet.next()) {
				item = (Temporada) Preencher(resultSet);
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
		Temporada item = new Temporada();
		item.setSerie(serie); 
		try {
			item.setIdTemporada(resultSet.getInt(resultSet.findColumn("idTemporada")));
			item.setSequencial(resultSet.getInt(resultSet.findColumn("sequencial")));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean Executar(Object objeto,String comando) {

		Temporada item = (Temporada) objeto;
		try {
			PreparedStatement preparedStmt;
			preparedStmt = Repositorio.con.prepareStatement(comando);
			preparedStmt.setInt(1, item.getSequencial());
			preparedStmt.setInt(2, serie.getIdSerie()); 
			Repositorio.Executar(preparedStmt); 
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}

