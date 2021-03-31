package br.com.prowayflix.model;

import br.com.prowayflix.interfaces.IDetalhe;

public class Perfil implements IDetalhe {
	private int IdPerfil;
	private String Nome;

	public Perfil() {
	}

	public Perfil(String nome) {
		setNome(nome);
	}
	public Perfil(int idPerfil, String nome) {
		setIdPerfil(idPerfil);
		setNome(nome);
	}

	public int getIdPerfil() {
		return IdPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		IdPerfil = idPerfil;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	@Override
	public void ExibirDetalhes() {
		System.out.println("Id: " + getIdPerfil() + " Perfil:"
				+ getNome());
		
	}

}
