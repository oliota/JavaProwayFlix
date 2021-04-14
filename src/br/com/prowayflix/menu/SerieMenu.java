package br.com.prowayflix.menu;

import java.util.ArrayList;

import br.com.prowayflix.bd.CategoriaDao;
import br.com.prowayflix.bd.SerieDao;
import br.com.prowayflix.bd.TemporadaDao;
import br.com.prowayflix.interfaces.IMenu;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Serie;
import br.com.prowayflix.model.Temporada;

public class SerieMenu extends Menu implements IMenu {

	static SerieDao serieDao = new SerieDao();
	static CategoriaDao categoriaDao = new CategoriaDao();
	static TemporadaDao temporadaDao = new TemporadaDao();
	static String titulo = "Serie";

	@Override
	public void ExibirMenu() {

		getOpcoes().add("5 - Temporadas");
		do {
			System.out.println("\n\n======= menu " + titulo + " ===============");
			ListarOpcoes();
			CapturarEscolha();
			System.out.println("\n\n==============================");
		} while (!resposta.equalsIgnoreCase("0"));
		resposta = "";
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
			serieDao.ReadAll(null);
			break;
		case "2":
			System.out.println("Escolheu ADICIONAR");
			Serie novo = (Serie) Cadastro("completo");

			if (Validar(novo, "completo")) {
				serieDao.Create(novo);
			}
			break;
		case "3":
			System.out.println("Escolheu EDITAR");
			Serie original = (Serie) Cadastro("busca");
			if (Validar(original, "busca")) {
				Serie editado = (Serie) Cadastro("completo");
				if (Validar(editado, "completo")) {
					serieDao.Update(original, editado);
				}
			}
			break;
		case "4":
			System.out.println("Escolheu DELETAR");
			Serie deletar = (Serie) Cadastro("busca");
			if (Validar(deletar, "busca")) {
				serieDao.Delete(deletar);
			}
			break;
		case "5":
			System.out.println("Escolheu TEMPORADAS");
			Serie serie = (Serie) Cadastro("busca");
			if (Validar(serie, "busca")) { 
				Serie busca=(Serie) serieDao.Find(serie.getNome());
				if(busca!=null)
				new TemporadaMenu(busca).ExibirMenu();
				else
					System.out.println("nada encontrado");
			}
			break;
		default:
			System.out.println("Opção invalida");
		}

	}

	@Override
	public Object Cadastro(String tipo) {
		Serie item = new Serie();
		System.out.println("=============================");
		switch (tipo) {
		case "completo":
			System.out.println("Preencha as informações para cadastrar novos valores");
			System.out.println("Informe o nome do " + titulo);
			item.setNome(Limpar(scan.next()));

			System.out.println("Informe o ano do " + titulo);
			item.setAno(scanNumber.nextInt());

			System.out.println("Informe a sinopse do " + titulo);
			item.setSinopse(Limpar(scan.next()));

			categoriaDao.ReadAll(null);
			System.out.println("Escolha uma categoria (selecione o nome)");
			Categoria categoria = (Categoria) categoriaDao.Find(Limpar(scan.next()));
			item.setCategoria(categoria);

			break;
		case "busca":

			System.out.println("Preencha as informações para realizar a busca na base");
			System.out.println("Informe o nome do " + titulo);
			item.setNome(Limpar(scan.next()));
			break;
		}
		return item;
	}

	@Override
	public boolean Validar(Object objeto, String tipo) {
		Serie item = (Serie) objeto;
		StringBuilder sbErros = new StringBuilder();
		switch (tipo) {
		case "completo":
			if (item.getNome() == null || item.getNome().isEmpty() || item.getNome().isBlank())
				sbErros.append("\nNome não pode ficar em branco");

			if (item.getAno() < 1800)
				sbErros.append("\nAno não pode ser menor que 1800");

			if (item.getAno() > 2021)
				sbErros.append("\nAno não pode ser maior que 2021");

			if (item.getSinopse() == null || item.getSinopse().isEmpty() || item.getSinopse().isBlank())
				sbErros.append("\nNome não pode ficar em branco");
			if (item.getCategoria() == null)
				sbErros.append("\nNão foi informada uma categoria valida.");

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
