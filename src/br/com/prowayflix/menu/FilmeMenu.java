package br.com.prowayflix.menu;

import java.util.Scanner;

import br.com.prowayflix.bd.CategoriaDao;
import br.com.prowayflix.bd.FilmeDao;
import br.com.prowayflix.interfaces.IMenu;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Filme; 

public class FilmeMenu extends Menu implements IMenu {

	static FilmeDao filmeDao = new FilmeDao();
	static CategoriaDao categoriaDao= new CategoriaDao();
	static String titulo = "Filme";

	@Override
	public void ExibirMenu() {
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
			filmeDao.ReadAll(null);
			break;
		case "2":
			System.out.println("Escolheu ADICIONAR");
			Filme novo = (Filme) Cadastro("completo");
			  categoriaDao= new CategoriaDao(); 
			novo.setCategoria((Categoria) categoriaDao.Find("Ação"));
			if (Validar(novo, "completo")) {
				filmeDao.Create(novo);
			}
			break;
		case "3":
			System.out.println("Escolheu EDITAR");
			Filme original = (Filme) Cadastro("busca");
			if (Validar(original, "busca")) {
				Filme editado = (Filme) Cadastro("completo");
				  categoriaDao= new CategoriaDao(); 
				  editado.setCategoria((Categoria) categoriaDao.Find("Ação"));
				if (Validar(editado, "completo")) {
					filmeDao.Update(original, editado);
				}
			}
			break;
		case "4":
			System.out.println("Escolheu DELETAR");
			Filme deletar = (Filme) Cadastro("busca");
			if (Validar(deletar, "busca")) {
				filmeDao.Delete(deletar);
			}
			break;
		default:
			System.out.println("Opção invalida");
		}

	}

	@Override
	public Object Cadastro(String tipo) {
		Filme item = new Filme(); 
		System.out.println("=============================");
		switch (tipo) {
		case "completo":
			System.out.println("Preencha as informações para cadastrar novos valores");
			System.out.println("Informe o nome do "+titulo);
			item.setNome(scan.next());

			System.out.println("Informe o ano do "+titulo);
			item.setAno(scanNumber.nextInt()); 

			System.out.println("Informe o nome do "+titulo);
			item.setSinopse(scan.next());

			break;
		case "busca":

			System.out.println("Preencha as informações para realizar a busca na base");
			System.out.println("Informe o nome do "+titulo);
			item.setNome(scan.next());
			break;
		}
		return item;
	}

	@Override
	public boolean Validar(Object objeto, String tipo) {
		Filme item = (Filme) objeto;
		StringBuilder sbErros = new StringBuilder();
		switch (tipo) {
		case "completo":
			if (item.getNome()==null || item.getNome().isEmpty() || item.getNome().isBlank())
				sbErros.append("\nNome não pode ficar em branco"); 
			 
			if (item.getAno()<1800)
				sbErros.append("\nAno não pode ser menor que 1800");
			
			if (item.getAno()>2021)
				sbErros.append("\nAno não pode ser maior que 2021");
			
			if (item.getSinopse()==null || item.getSinopse().isEmpty() || item.getSinopse().isBlank())
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

