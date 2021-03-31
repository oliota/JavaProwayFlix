package br.com.prowayflix.model;

import java.util.ArrayList;

public class Temporada {
	private int IdTemporada;
	private Serie Serie;
	private int Sequencial;
	private ArrayList<Episodio> Episodios;

	 
	public Temporada() { 
		setEpisodios(new ArrayList<Episodio>());
	}

	public Temporada(int sequencial) {
		setSequencial(sequencial);
		setEpisodios(new ArrayList<Episodio>());
	}

	public Temporada(int sequencial, ArrayList<Episodio> episodios) {
		setSequencial(sequencial);
		setEpisodios(episodios);
	}

	public Temporada(int idTemporada, Serie serie, int sequencial, ArrayList<Episodio> episodios) {
		setIdTemporada(idTemporada);
		setSerie(serie);
		setSequencial(sequencial);
		setEpisodios(episodios);
	}

	public int getIdTemporada() {
		return IdTemporada;
	}

	public void setIdTemporada(int idTemporada) {
		IdTemporada = idTemporada;
	}

	public Serie getSerie() {
		return Serie;
	}

	public void setSerie(Serie serie) {
		Serie = serie;
	}

	public int getSequencial() {
		return Sequencial;
	}

	public void setSequencial(int sequencial) {
		Sequencial = sequencial;
	}

	public ArrayList<Episodio> getEpisodios() {
		return Episodios;
	}

	public void setEpisodios(ArrayList<Episodio> episodios) {
		Episodios = episodios;
	}

}
