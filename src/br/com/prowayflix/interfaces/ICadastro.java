package br.com.prowayflix.interfaces;

import java.sql.ResultSet;

public interface ICadastro {
	public Object Create(Object novo);
	public Object ReadAll(Object pai);
	public Object Update(Object atual,Object editado);
	public boolean Delete(Object item);

	public Object Find(Object chave);
	public Object Preencher(ResultSet resultSet);

}
