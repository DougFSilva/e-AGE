package com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades;

import com.dougfsilva.e_AGE.dominio.curso.Curso;
import com.dougfsilva.e_AGE.dominio.curso.areatecnologica.AreaTecnologica;
import com.dougfsilva.e_AGE.dominio.pessoa.Pessoa;

public class GeraNomeDeImagem {

	public static String pelaAreaTecnologica(AreaTecnologica area) {
		return area.getID() + "-" + area.getTitulo(); 
	}
	
	public static String peloCurso(Curso curso) {
		return curso.getID() + "-" + curso.getTitulo(); 
	}
	
	public static String pelaPessoa(Pessoa pessoa) {
		return pessoa.getID() + "-" + pessoa.getNome(); 
	}
	
}
