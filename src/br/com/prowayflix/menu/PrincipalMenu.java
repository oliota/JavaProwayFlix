package br.com.prowayflix.menu;

import java.util.ArrayList;

import br.com.prowayflix.interfaces.IMenu;

public class PrincipalMenu  extends Menu implements IMenu {

	@Override
	public void ExibirMenu() {
		setOpcoes(new ArrayList<String>());
		getOpcoes().add("0 - Sair");
		getOpcoes().add("1 - Perfis");
		getOpcoes().add("2 - Categorias");
		getOpcoes().add("3 - Filmes");
		getOpcoes().add("4 - Series");
		do {
			System.out.println("\n\n==============================");
			ListarOpcoes();
			CapturarEscolha();
			System.out.println("\n\n==============================");
		} while (!resposta.equalsIgnoreCase("0")); 
		resposta="";
	}

	@Override
	public void ListarOpcoes() {
		for (int i = 0; i < getOpcoes().size(); i++) {
			System.out.println(getOpcoes().get(i));
		}
	}

	@Override
	public void CapturarEscolha() {
		super.CapturarEscolha();
		switch (resposta) {
		case "0":
			System.out.println("Escolheu SAIR");
			break;
		case "1":
			System.out.println("Escolheu PERFIS");
			new PerfilMenu().ExibirMenu();
			break;
		case "2":
			System.out.println("Escolheu CATEGORIAS");
			new CategoriaMenu().ExibirMenu();
			break;
		case "3":
			System.out.println("Escolheu FILMES");
			new FilmeMenu().ExibirMenu();
			break;
		case "4":
			System.out.println("Escolheu SERIES");
			new SerieMenu().ExibirMenu();
			break;
		default:
			System.out.println("Opção invalida");
		}

	}

	@Override
	public Object Cadastro(String tipo) {
		return null;
	}

	@Override
	public boolean Validar(Object objeto, String tipo) {
		return true;
	}
}
