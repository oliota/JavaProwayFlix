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
		do {
			System.out.println("\n\n==============================");
			ListarOpcoes();
			CapturarEscolha();
			System.out.println("\n\n==============================");
		} while (!resposta.equalsIgnoreCase("0"));
	}

	@Override
	public void ListarOpcoes() {
		for (int i = 0; i < getOpcoes().size(); i++) {
			System.out.println(getOpcoes().get(i));
		}
	}

	@Override
	public void CapturarEscolha() {
		System.out.println("Escolha uma opção da lista");
		resposta = scan.next();
		switch (resposta) {
		case "0":
			System.out.println("Escolheu SAIR");
			break;
		case "1":
			System.out.println("Escolheu PERFIS");
			PerfilMenu perfilMenu = new PerfilMenu();
			perfilMenu.ExibirMenu();
			break;
		case "2":
			System.out.println("Escolheu CATEGORIAS");
			break;
		case "3":
			System.out.println("Escolheu FILMES");
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
