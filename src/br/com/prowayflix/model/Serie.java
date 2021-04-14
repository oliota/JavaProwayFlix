package br.com.prowayflix.model;

import java.util.ArrayList;

import br.com.prowayflix.interfaces.IDetalhe;

public class Serie implements IDetalhe{
	private int IdSerie;
	private Categoria Categoria;
	private String Nome;
	private int Ano;
	private String Sinopse;
	private ArrayList<Temporada> Temporadas;

	public Serie() { 
		setTemporadas(new ArrayList<Temporada>());
	}
	public Serie(String nome, int ano, String sinopse) {
		setNome(nome);
		setAno(ano);
		setSinopse(sinopse);
		setTemporadas(new ArrayList<Temporada>());
	}

	public Serie(String nome, int ano, String sinopse, ArrayList<Temporada> temporadas) {
		setNome(nome);
		setAno(ano);
		setSinopse(sinopse);
		setTemporadas(temporadas);
	}

	public Serie(int idSerie, Categoria categoria, String nome, int ano, String sinopse,
			ArrayList<Temporada> temporadas) {
		setIdSerie(idSerie);
		setCategoria(categoria);
		setNome(nome);
		setAno(ano);
		setSinopse(sinopse);
		setTemporadas(temporadas);
	}

	public int getIdSerie() {
		return IdSerie;
	}

	public void setIdSerie(int idSerie) {
		IdSerie = idSerie;
	}

	public Categoria getCategoria() {
		return Categoria;
	}

	public void setCategoria(Categoria categoria) {
		Categoria = categoria;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getAno() {
		return Ano;
	}

	public void setAno(int ano) {
		Ano = ano;
	}

	public String getSinopse() {
		return Sinopse;
	}

	public void setSinopse(String sinopse) {
		Sinopse = sinopse;
	}

	public ArrayList<Temporada> getTemporadas() {
		return Temporadas;
	}

	public void setTemporadas(ArrayList<Temporada> temporadas) {
		Temporadas = temporadas;
	}
	@Override
	public void ExibirDetalhes() {
		System.out.println("Id: " + getIdSerie() +
				" Serie:" + getNome()+
				" Ano:" + getAno()+
				" Categoria:" + getCategoria().getNome()
				);
	}

}
