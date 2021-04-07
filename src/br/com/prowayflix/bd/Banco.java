package br.com.prowayflix.bd;

public class Banco {

	public Banco() {
		if(Repositorio.con==null) {
			System.out.println("banco inicializado");
			Repositorio.ConectarBd("postgres");
		}
	}

}
