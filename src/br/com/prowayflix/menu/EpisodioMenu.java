package br.com.prowayflix.menu;

import br.com.prowayflix.bd.CategoriaDao;
import br.com.prowayflix.bd.EpisodioDao;
import br.com.prowayflix.bd.SerieDao;
import br.com.prowayflix.bd.TemporadaDao;
import br.com.prowayflix.interfaces.IMenu;
import br.com.prowayflix.model.Categoria;
import br.com.prowayflix.model.Episodio;
import br.com.prowayflix.model.Filme;
import br.com.prowayflix.model.Serie;
import br.com.prowayflix.model.Temporada;

public class EpisodioMenu extends Menu implements IMenu {
	 
		static EpisodioDao episodioDao = new EpisodioDao(); 
		static String titulo = "Episodio";
		private Temporada pai;
		
		public EpisodioMenu() {
		}

		public EpisodioMenu(Temporada pai) {
			setPai(pai);
			episodioDao.setTemporada(pai);
		}

		@Override
		public void ExibirMenu() {
			do {
				System.out.println("\n\n======= menu "+titulo+" - Temporada ["+pai.getSequencial()+"] - Serie:"+pai.getSerie().getNome()+"  ===============");
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
				episodioDao.ReadAll(pai);
				break;
			case "2":
				System.out.println("Escolheu ADICIONAR");
				Episodio novo = (Episodio) Cadastro("completo"); 
				if (Validar(novo, "completo")) {
					episodioDao.Create(novo);
				}
				break;
			case "3":
				System.out.println("Escolheu EDITAR");
				Episodio original = (Episodio) Cadastro("busca");
				if (Validar(original, "busca")) {
					Episodio editado = (Episodio) Cadastro("completo");
					if (Validar(editado, "completo")) {
						episodioDao.Update(original, editado);
					}
				}
				break;
			case "4":
				System.out.println("Escolheu DELETAR");
				Episodio deletar = (Episodio) Cadastro("busca");
				if (Validar(deletar, "busca")) {
					episodioDao.Delete(deletar);
				}
				break;
			default:
				System.out.println("Opção invalida");
			}

		}

		@Override
		public Object Cadastro(String tipo) {
			Episodio item = new Episodio();
			System.out.println("=============================");
			switch (tipo) {
			case "completo":
				System.out.println("Preencha as informações para cadastrar novos valores");
				System.out.println("Informe o sequencial do " + titulo);
				item.setSequencial(scanNumber.nextInt()); 

				System.out.println("Informe o nome do " + titulo);
				item.setNome(Limpar(scan.next())); 

				System.out.println("Informe a sinopse do " + titulo);
				item.setNome(Limpar(scan.next())); 
				
				item.setTemporada(pai);

				break;
			case "busca":

				System.out.println("Preencha as informações para realizar a busca na base");
				System.out.println("Informe o sequencial do " + titulo);
				item.setSequencial(scanNumber.nextInt()); 
				item.setTemporada(pai);
				break;
			}
			return item;
		}

		@Override
		public boolean Validar(Object objeto, String tipo) {
			Episodio item = (Episodio) objeto;
			StringBuilder sbErros = new StringBuilder();
			switch (tipo) {
			case "completo": 
				if (item.getSequencial() <0)
					sbErros.append("\nSequencial não pode ser menor que zero"); 
				
				break;
			case "busca":
				if (item.getSequencial() <0)
					sbErros.append("\nSequencial não pode ser menor que zero"); 
				break;
			}
			if (!sbErros.toString().isEmpty()) {
				System.out.println("Não é possivel prosseguir com a operação, verifique os motivos:");
				System.out.println(sbErros.toString());
				return false;

			}
			return true;
		}

		public Temporada getPai() {
			return pai;
		}

		public void setPai(Temporada pai) {
			this.pai = pai;
		}

		 

	}

