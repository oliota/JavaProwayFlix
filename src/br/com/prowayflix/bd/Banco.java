package br.com.prowayflix.bd;

public class Banco {

	public Banco() {
		System.out.println("banco inicializado");
		if(Repositorio.con==null) {
			Repositorio.ConectarBd("postgres");
		}
	}

}
