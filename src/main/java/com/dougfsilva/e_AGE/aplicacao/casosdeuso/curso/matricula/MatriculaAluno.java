package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.MatriculaAlunoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
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
	private final ValidaMatricula validador;
	
	public MatriculaResposta matricular(MatriculaAlunoForm form) {
		Matricula matricula = construirMatricula(form);
		return MatriculaResposta.deMatricula(repository.salvar(matricula));
	}
	
	private Matricula construirMatricula(MatriculaAlunoForm form) {
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(form.alunoID());
		validador.validarUnicoAlunoPorModulo(modulo, aluno);
		validador.validarUnicoRegistro(form.registro());
		return new Matricula(form.registro(), modulo, aluno, form.dataDaMatricula(), MatriculaStatus.MATRICULA_ATIVA);
	}
	
}
