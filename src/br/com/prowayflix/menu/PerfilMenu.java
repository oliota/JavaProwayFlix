package br.com.prowayflix.menu;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.prowayflix.bd.PerfilDao;
import br.com.prowayflix.interfaces.IMenu;
import br.com.prowayflix.model.Perfil;

public class PerfilMenu extends Menu implements IMenu {

	static Scanner scan = new Scanner(System.in);
	static String resposta;
	static PerfilDao perfilDao = new PerfilDao();

	@Override
	public void ExibirMenu() {
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
			perfilDao.ReadAll(null);
			break;
		case "2":
			System.out.println("Escolheu ADICIONAR");
			Perfil novo = (Perfil) Cadastro("completo");
			if (Validar(novo, "completo")) {
				perfilDao.Create(novo);
			}
			break;
		case "3":
			System.out.println("Escolheu EDITAR");
			Perfil original = (Perfil) Cadastro("busca");
			if (Validar(original, "busca")) {
				Perfil editado = (Perfil) Cadastro("completo");
				if (Validar(editado, "completo")) {
					perfilDao.Update(original, editado);
				}
			}
			break;
		case "4":
			System.out.println("Escolheu DELETAR");
			Perfil deletar = (Perfil) Cadastro("busca");
			if (Validar(deletar, "busca")) {
				perfilDao.Delete(deletar);
			}
			break;
		default:
			System.out.println("Opção invalida");
		}

	}

	@Override
	public Object Cadastro(String tipo) {
		Perfil item = new Perfil(); 
		System.out.println("=============================");
		switch (tipo) {
		case "completo":
			System.out.println("Preencha as informações para cadastrar novos valores");
			System.out.println("Informe o nome do perfil:");
			item.setNome(scan.next());
			break;
		case "busca":

			System.out.println("Preencha as informações para realizar a busca na base");
			System.out.println("Informe o nome do perfil:");
			item.setNome(scan.next());
			break;
		}
		return item;
	}

	@Override
	public boolean Validar(Object objeto, String tipo) {
		Perfil item = (Perfil) objeto;
		StringBuilder sbErros = new StringBuilder();
		switch (tipo) {
		case "completo":
			if (item.getNome().isEmpty() || item.getNome().isBlank())
				sbErros.append("\nNome não pode ficar em branco");
			break;
		case "busca":
			if (item.getNome().isEmpty() || item.getNome().isBlank())
				sbErros.append("\nNome não pode ficar em branco");
			break;
		}
		if (!sbErros.toString().isEmpty()) {
			System.out.println("Não é possivelo prosseguir com a operação, verifique os motivos:");
			System.out.println(sbErros.toString());
			return false;

		}
		return true;
	}

}
