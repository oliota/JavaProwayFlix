package br.com.prowayflix.bd;

import java.sql.ResultSet;

import br.com.prowayflix.interfaces.ICadastro;
import br.com.prowayflix.model.Categoria;

public class CategoriaDao implements ICadastro {

	private final String TABELA = "Categoria";

	@Override
	public Object Create(Object novo) {
		Categoria item = (Categoria) novo;
		if (Find(item.getNome()) != null) {
			System.out.println("Erro ao adicionar. Já existe um registro com nome " + item.getNome());
		} else {
			item.setIdCategoria(Repositorio.Categorias.size());
			Repositorio.Categorias.add(item);
			item.ExibirDetalhes();
			System.out.println("Salvo com sucesso!");
		}
		return item;
	}

	@Override
	public Object ReadAll(Object pai) {
		if (Repositorio.Categorias.size() == 0) {
			System.out.println("Não há itens para exibir");
		} else {
			System.out.println("\n\n====== Listar "+TABELA+" ========");
			for (int i = 0; i < Repositorio.Categorias.size(); i++) {
				Repositorio.Categorias.get(i).ExibirDetalhes();
			}
			System.out.println("====== Itens:" + Repositorio.Categorias.size() + " ========\n\n");
		}
		return Repositorio.Categorias;
	}

	@Override
	public Object Update(Object atual, Object editado) {
		Categoria original = (Categoria) atual;
		Categoria atualizado = (Categoria) editado;
		original = (Categoria) Find(original.getNome());
		if (original == null) {
			System.out.println("Erro ao editar. Nada foi encontrado com nome " + original.getNome());
		} else if (Find(atualizado.getNome()) != null) {
			System.out.println("Erro ao editar. Já existe um registro com nome " + atualizado.getNome());
		}else {
			System.out.println("====== Antes ========");
			original.ExibirDetalhes();
			System.out.println("====== Depois ========");
			original.setNome(atualizado.getNome());
			original.ExibirDetalhes();
			System.out.println("Atualizado com sucesso!");
		}
		return original;
	}

	@Override
	public boolean Delete(Object item) {
		Categoria deletado = (Categoria) item;
		Categoria busca = (Categoria) Find(deletado.getNome());
		if (busca != null) {
			System.out.println("Item deletado com sucesso!");
			return Repositorio.Categorias.remove(busca);
		} else {
			System.out.println("Erro ao deletar. Nada foi encontrado com nome " + deletado.getNome());
			return false;
		}
	}

	@Override
	public Object Find(Object chave) {
		String nome = (String) chave;
		for (int i = 0; i < Repositorio.Categorias.size(); i++) {
			if (Repositorio.Categorias.get(i).getNome().equalsIgnoreCase(nome)) {
				return Repositorio.Categorias.get(i);
			}
		}
		return null;
	}

	@Override
	public Object Preencher(ResultSet resultSet) {
		// TODO Auto-generated method stub
		return null;
	}

}
