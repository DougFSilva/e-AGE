package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.MatriculaAlunoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatriculaAluno {

	private final MatriculaRepository repository;
	private final ModuloRepository moduloRepository;
	private final AlunoRepository alunoRepository;
	private final LogPadrao log;
	
	public MatriculaResposta matricular(MatriculaAlunoForm form) {
		
	}
	
	private Matricula construirMatricula(MatriculaAlunoForm form) {
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(form.alunoID());
		
	}
	
}
