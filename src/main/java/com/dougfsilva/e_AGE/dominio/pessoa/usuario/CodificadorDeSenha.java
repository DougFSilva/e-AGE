package com.dougfsilva.e_AGE.dominio.pessoa.usuario;

public interface CodificadorDeSenha {

	String codificar(String senha);
	
	Boolean comparar(String senha, String senhaCodificada);
}
