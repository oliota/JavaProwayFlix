package br.com.prowayflix.model;

import br.com.prowayflix.interfaces.IDetalhe;

public class Filme implements IDetalhe {
	private int IdFilme;
	private Categoria Categoria;
	private String Nome;
	private int Ano;
	private String Sinopse;

	public Filme() {

	}

	public Filme(String nome, int ano, String sinopse) {
		setNome(nome);
		setAno(ano);
		setSinopse(sinopse);
	}

	public Filme(int idFilme, Categoria categoria, String nome, int ano, String sinopse) {
		setIdFilme(idFilme);
		setCategoria(categoria);
		setNome(nome);
		setAno(ano);
		setSinopse(sinopse);
	}

	public int getIdFilme() {
		return IdFilme;
	}

	public void setIdFilme(int idFilme) {
		IdFilme = idFilme;
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

	@Override
	public void ExibirDetalhes() {
		System.out.println("Id: " + getIdFilme() +
				" Filme:" + getNome()+
				" Ano:" + getAno()+
				" Categoria:" + getCategoria().getNome()
				);
		
	}

}
