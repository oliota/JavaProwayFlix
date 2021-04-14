package br.com.prowayflix.menu;

import br.com.prowayflix.bd.CategoriaDao;
import br.com.prowayflix.interfaces.IMenu;
import br.com.prowayflix.model.Categoria;

public class CategoriaMenu extends Menu implements IMenu {

	static CategoriaDao categoriaDao = new CategoriaDao();
	static String titulo = "Categoria";

	@Override
	public void ExibirMenu() {
		do {
			System.out.println("\n\n======= menu "+titulo+" ===============");
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
			categoriaDao.ReadAll(null);
			break;
		case "2":
			System.out.println("Escolheu ADICIONAR");
			Categoria novo = (Categoria) Cadastro("completo");
			if (Validar(novo, "completo")) {
				categoriaDao.Create(novo);
			}
			break;
		case "3":
			System.out.println("Escolheu EDITAR");
			Categoria original = (Categoria) Cadastro("busca");
			if (Validar(original, "busca")) {
				Categoria editado = (Categoria) Cadastro("completo");
				if (Validar(editado, "completo")) {
					categoriaDao.Update(original, editado);
				}
			}
			break;
		case "4":
			System.out.println("Escolheu DELETAR");
			Categoria deletar = (Categoria) Cadastro("busca");
			if (Validar(deletar, "busca")) {
				categoriaDao.Delete(deletar);
			}
			break;
		default:
			System.out.println("Opção invalida");
		}

	}

	@Override
	public Object Cadastro(String tipo) {
		Categoria item = new Categoria(); 
		System.out.println("=============================");
		switch (tipo) {
		case "completo":
			System.out.println("Preencha as informações para cadastrar novos valores");
			System.out.println("Informe o nome do "+titulo);
			item.setNome(Limpar(scan.next()));
			break;
		case "busca":

			System.out.println("Preencha as informações para realizar a busca na base");
			System.out.println("Informe o nome do "+titulo);
			item.setNome(Limpar(scan.next()));
			break;
		}
		return item;
	}

	@Override
	public boolean Validar(Object objeto, String tipo) {
		Categoria item = (Categoria) objeto;
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

