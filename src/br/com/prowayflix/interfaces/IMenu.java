package br.com.prowayflix.interfaces;

public interface IMenu {

	public void ExibirMenu();
	public void ListarOpcoes();
	public void CapturarEscolha();
	public Object Cadastro(String tipo); 

	public boolean Validar(Object objeto,String tipo);
	
	

}
