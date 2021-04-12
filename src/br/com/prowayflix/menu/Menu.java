package br.com.prowayflix.menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	static Scanner scan = new Scanner(System.in).useDelimiter("\n");
	static Scanner scanNumber = new Scanner(System.in);
	static String resposta;
	private ArrayList<String> Opcoes;

	public Menu() {
		Opcoes = new ArrayList<String>();
		Opcoes.add("0 - Sair");
		Opcoes.add("1 - Listar");
		Opcoes.add("2 - Add");
		Opcoes.add("3 - Edit");
		Opcoes.add("4 - Del");
	}

	public ArrayList<String> getOpcoes() {
		return Opcoes;
	}

	public void setOpcoes(ArrayList<String> opcoes) {
		this.Opcoes = opcoes;
	}
	
	public void CapturarEscolha() {
		System.out.println("Escolha uma opção da lista");
		resposta = scan.next().replaceAll("\r","");
	}

}
