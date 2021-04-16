package br.com.prowayflix.menu;

import br.com.prowayflix.bd.CategoriaDao; 
import br.com.prowayflix.bd.TemporadaDao;
import br.com.prowayflix.interfaces.IMenu;
import br.com.prowayflix.model.Categoria; 
import br.com.prowayflix.model.Serie;
import br.com.prowayflix.model.Temporada;

public class TemporadaMenu extends Menu implements IMenu {
 
	static TemporadaDao temporadaDao = new TemporadaDao(); 
	static String titulo = "Temporada";
	private Serie pai;
	
	public TemporadaMenu() {
	}

	public TemporadaMenu(Serie pai) {
		setPai(pai);
		temporadaDao.setSerie(pai);
	}

	@Override
	public void ExibirMenu() {
		getOpcoes().add("5 - Episodios");
		do {
			System.out.println("\n\n======= menu "+titulo+" - Serie - "+pai.getNome()+"  ===============");
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
			temporadaDao.ReadAll(pai);
			break;
		case "2":
			System.out.println("Escolheu ADICIONAR");
			Temporada novo = (Temporada) Cadastro("completo");

			if (Validar(novo, "completo")) {
				temporadaDao.Create(novo);
			}
			break;
		case "3":
			System.out.println("Escolheu EDITAR");
			Temporada original = (Temporada) Cadastro("busca");
			if (Validar(original, "busca")) {
				Temporada editado = (Temporada) Cadastro("completo");
				if (Validar(editado, "completo")) {
					temporadaDao.Update(original, editado);
				}
			}
			break;
		case "4":
			System.out.println("Escolheu DELETAR");
			Temporada deletar = (Temporada) Cadastro("busca");
			if (Validar(deletar, "busca")) {
				temporadaDao.Delete(deletar);
			}
			break;

		case "5": {
			System.out.println("Escolheu EPISODIOS");
			Temporada serie = (Temporada) Cadastro("busca");
			if (Validar(serie, "busca")) {
				Temporada busca = (Temporada) temporadaDao.Find(serie.getSequencial());
				if (busca != null)
					new EpisodioMenu(busca).ExibirMenu();
				else
					System.out.println("nada encontrado");
			}
		}
			break;
		default:
			System.out.println("Opção invalida");
		}

	}

	@Override
	public Object Cadastro(String tipo) {
		Temporada item = new Temporada();
		System.out.println("=============================");
		switch (tipo) {
		case "completo":
			System.out.println("Preencha as informações para cadastrar novos valores");
			System.out.println("Informe o sequencial do " + titulo);
			item.setSequencial(scanNumber.nextInt()); 
			item.setSerie(pai);

			break;
		case "busca":

			System.out.println("Preencha as informações para realizar a busca na base");
			System.out.println("Informe o sequencial do " + titulo);
			item.setSequencial(scanNumber.nextInt()); 
			item.setSerie(pai);
			break;
		}
		return item;
	}

	@Override
	public boolean Validar(Object objeto, String tipo) {
		Temporada item = (Temporada) objeto;
		StringBuilder sbErros = new StringBuilder();
		switch (tipo) {
		case "completo": 
			if (item.getSequencial() <0)
				sbErros.append("\nAno não pode ser menor que zero"); 
			
			break;
		case "busca":
			if (item.getSequencial() <0)
				sbErros.append("\nAno não pode ser menor que zero"); 
			break;
		}
		if (!sbErros.toString().isEmpty()) {
			System.out.println("Não é possivelo prosseguir com a operação, verifique os motivos:");
			System.out.println(sbErros.toString());
			return false;

		}
		return true;
	}

	public Serie getPai() {
		return pai;
	}

	public void setPai(Serie pai) {
		this.pai = pai;
	}

}

