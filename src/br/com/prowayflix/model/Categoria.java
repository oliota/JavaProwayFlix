package br.com.prowayflix.model;

import br.com.prowayflix.bd.Repositorio;
import br.com.prowayflix.interfaces.IDetalhe;

public class Categoria implements IDetalhe{
	private int IdCategoria;
	private String Nome;
	public Categoria() {
		
	}

	public Categoria(String nome) {
		Nome = nome;
	}
	
	public Categoria(int idCategoria, String nome) {
		IdCategoria = idCategoria;
		Nome = nome;
	}
	public int getIdCategoria() {
		return IdCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		IdCategoria = idCategoria;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}

	@Override
	public void ExibirDetalhes() {
		System.out.println("Id: " + getIdCategoria() + " Categoria:"
				+ getNome());
		
	}
	
	

}
