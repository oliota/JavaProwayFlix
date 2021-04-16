package br.com.prowayflix.model;

import br.com.prowayflix.interfaces.IDetalhe;

public class Episodio implements IDetalhe{
	private int IdEpisodio;
	private Temporada Temporada;
	private int Sequencial;
	private String Nome;
	private String Sinopse;

	public Episodio() {
	}

	public Episodio(int sequencial, String nome, String sinopse) {
		setSequencial(sequencial);
		setNome(nome);
		setSinopse(sinopse);
	}

	public Episodio(int idEpisodio, Temporada temporada, int sequencial, String nome, String sinopse) {
		setIdEpisodio(idEpisodio);
		setTemporada(temporada);
		setSequencial(sequencial);
		setNome(nome);
		setSinopse(sinopse);
	}

	public int getIdEpisodio() {
		return IdEpisodio;
	}

	public void setIdEpisodio(int idEpisodio) {
		IdEpisodio = idEpisodio;
	}

	public Temporada getTemporada() {
		return Temporada;
	}

	public void setTemporada(Temporada temporada) {
		Temporada = temporada;
	}

	public int getSequencial() {
		return Sequencial;
	}

	public void setSequencial(int sequencial) {
		Sequencial = sequencial;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getSinopse() {
		return Sinopse;
	}

	public void setSinopse(String sinopse) {
		Sinopse = sinopse;
	}

	@Override
	public void ExibirDetalhes() {
		System.out.println("Id: " + getIdEpisodio() +
				" Episodio:" + getNome()+
				" Sequencial:" + getSequencial()+
				" Temporada:" + getTemporada().getSequencial()
				);
		
	}

}
