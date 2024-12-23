package com.dougfsilva.e_AGE.dominio.pessoa.funcionario;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Cargo {

	DIRETOR("Liderança estratégica geral e gestão da escola"),
	COORDENADOR("Responsável por coordenar projetos, equipes ou atividades específicas na escola"),
	PROFESSOR("Responsável por ensino, pesquisa e mentoria de estudantes."),
	AQV("Responsável pela garantia da qualidade de vida e melhoria contínua dos processos e serviços da escola"),
	SECRETARIA("Oferece suporte administrativo, organização e assistência"),
	BIBLIOTECARIA("Gerencia a biblioteca da escola e seus recursos"),
	OUTRO("Outro cargo não específico");

	private String descricao;

	private Cargo(String descricao) {
	    this.descricao = descricao;
	}
}
