package br.com.prowayflix.menu;

import br.com.prowayflix.bd.CategoriaDao;
import br.com.prowayflix.bd.FilmeDao;
import br.com.prowayflix.interfaces.IMenu;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Filme;

public class FilmeMenu extends Menu implements IMenu {

	static FilmeDao filmeDao = new FilmeDao();
	static CategoriaDao categoriaDao = new CategoriaDao();
	static String titulo = "Filme";

	@Override
	public void ExibirMenu() {
		do {
			System.out.println("\n\n======= menu "+titulo+" ===============");
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
			filmeDao.ReadAll(null);
			break;
		case "2":
			System.out.println("Escolheu ADICIONAR");
			Filme novo = (Filme) Cadastro("completo");

			if (Validar(novo, "completo")) {
				filmeDao.Create(novo);
			}
			break;
		case "3":
			System.out.println("Escolheu EDITAR");
			Filme original = (Filme) Cadastro("busca");
			if (Validar(original, "busca")) {
				Filme editado = (Filme) Cadastro("completo"); 
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
			System.out.println("Op??o invalida");
		}

	}

	@Override
	public Object Cadastro(String tipo) {
		Filme item = new Filme();
		System.out.println("=============================");
		switch (tipo) {
		case "completo":
			System.out.println("Preencha as informa??es para cadastrar novos valores");
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

			System.out.println("Preencha as informa??es para realizar a busca na base");
			System.out.println("Informe o nome do " + titulo);
			item.setNome(Limpar(scan.next()));
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
			if (item.getNome() == null || item.getNome().isEmpty() || item.getNome().isBlank())
				sbErros.append("\nNome n?o pode ficar em branco");

			if (item.getAno() < 1800)
				sbErros.append("\nAno n?o pode ser menor que 1800");

			if (item.getAno() > 2021)
				sbErros.append("\nAno n?o pode ser maior que 2021");

			if (item.getSinopse() == null || item.getSinopse().isEmpty() || item.getSinopse().isBlank())
				sbErros.append("\nNome n?o pode ficar em branco");
			if(item.getCategoria()==null)
				sbErros.append("\nN?o foi informada uma categoria valida.");
			
			break;
		case "busca":
			if (item.getNome().isEmpty() || item.getNome().isBlank())
				sbErros.append("\nNome n?o pode ficar em branco");
			break;
		}
		if (!sbErros.toString().isEmpty()) {
			System.out.println("N?o ? possivelo prosseguir com a opera??o, verifique os motivos:");
			System.out.println(sbErros.toString());
			return false;

		}
		return true;
	}

}
