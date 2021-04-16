package br.com.prowayflix.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.prowayflix.interfaces.ICadastro;
import br.com.prowayflix.model.Episodio;
import br.com.prowayflix.model.Serie;
import br.com.prowayflix.model.Temporada;

public class EpisodioDao extends Banco implements ICadastro {

	private final String TABELA = "Episodio";
	private Temporada temporada;
	
	public EpisodioDao() { 
	}

	public EpisodioDao(Temporada temporada) {
		setTemporada(temporada);
	}
	
 
	@Override
	public Object Create(Object novo) {
		Episodio item = (Episodio) novo;
		if (Find(item.getSequencial()) != null) {
			System.out.println("Erro ao adicionar. Já existe registro com a chave " + item.getSequencial());
		} else {  
			if(Executar(item, "INSERT INTO " + TABELA + " (sequencial,temporadaid,nome,sinopse) VALUES (?,"+temporada.getIdTemporada()+",?,?)")) {
				item = (Episodio) Find(item.getSequencial());
				item.ExibirDetalhes();
				System.out.println("Salvo com sucesso!");
			}  
		}
		return item;
	}

	@Override
	public Object ReadAll(Object pai) { 
		ResultSet resultSet = Repositorio.ConsultarBD("SELECT t.idEpisodio,t.sequencial,t.nome episodio,t.sinopse "
				+ " from "+TABELA+" t "
				+ " where t.temporadaId="+temporada.getIdTemporada()
				);

		ArrayList<Episodio> lista = new ArrayList<>();
		try {
			while (resultSet.next()) {
				if (resultSet.isFirst()) {
					System.out.println("\n\n====== Listar " + TABELA + " ========");
				}
				Episodio item = (Episodio) Preencher(resultSet);
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
		Episodio formulario = (Episodio) atual;
		Episodio atualizado = (Episodio) editado;
		Episodio original = (Episodio) Find(formulario.getSequencial());
		if (original == null) {
			System.out.println("Erro ao editar. Nada foi encontrado com a chave " + formulario.getSequencial());
		} else if (Find(atualizado.getSequencial()) != null) {
			System.out.println("Erro ao editar. Já existe um registro com a chave " + atualizado.getSequencial());
		} else {
			System.out.println("====== EDITAR ========");
			System.out.println("====== Antes ========");
			original.ExibirDetalhes();
			System.out.println("====== Depois ========");
			
			if(Executar(atualizado, "UPDATE " + TABELA + " set sequencial=?, nome=?, sinopse=? "+ " WHERE temporadaId="+temporada.getIdTemporada()+" sequencial=" + original.getSequencial())) {
				original = (Episodio) Find(atualizado.getSequencial());
				original.ExibirDetalhes();
				System.out.println("Atualizado com sucesso!");
			}  
			
			
		}
		return original;
	}

	@Override
	public boolean Delete(Object item) {
		Episodio deletado = (Episodio) item;
		Episodio busca = (Episodio) Find(deletado.getSequencial());
		if (busca != null) {
			Repositorio.ExecutarComandoBD("DELETE FROM " + TABELA +  " WHERE temporadaId="+temporada.getIdTemporada()+" and sequencial=" + busca.getSequencial());
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
		Episodio item = null;
		
		ResultSet resultSet = Repositorio.ConsultarBD( "SELECT t.idEpisodio,t.sequencial,t.nome episodio,t.sinopse  "
				+ " from "+TABELA+" t "
				+ " where t.temporadaid="+temporada.getIdTemporada()
						+ " and t.sequencial="+sequencial
				);
		

		try {
			while (resultSet.next()) {
				item = (Episodio) Preencher(resultSet);
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
		Episodio item = new Episodio();
		item.setTemporada(temporada); 
		try {
			item.setIdEpisodio(resultSet.getInt(resultSet.findColumn("idepisodio")));
			item.setSequencial(resultSet.getInt(resultSet.findColumn("sequencial")));
			item.setNome(resultSet.getString(resultSet.findColumn("episodio")));
			item.setSinopse(resultSet.getString(resultSet.findColumn("sinopse")));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean Executar(Object objeto,String comando) {

		Episodio item = (Episodio) objeto;
		try {
			PreparedStatement preparedStmt;
			preparedStmt = Repositorio.con.prepareStatement(comando);
			preparedStmt.setInt(1, item.getSequencial());
			preparedStmt.setString(2, item.getNome()); 
			preparedStmt.setString(3, item.getSinopse()); 
			Repositorio.Executar(preparedStmt); 
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Temporada getTemporada() {
		return temporada;
	}

	public void setTemporada(Temporada teporada) {
		this.temporada = teporada;
	}

}

