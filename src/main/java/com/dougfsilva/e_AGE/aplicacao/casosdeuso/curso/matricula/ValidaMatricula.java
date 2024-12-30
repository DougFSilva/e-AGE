package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeMatriculaException;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidaMatricula {

	private final MatriculaRepository repository;
	
	public void validarUnicoAlunoPorModulo(Modulo modulo, Aluno aluno) {
		if (repository.existePeloModuloEAluno(modulo, aluno)) {
			throw new ErroDeValidacaoDeMatriculaException(String.format("Aluno %s já existe no módulo %s", aluno.getNome() , modulo.getCodigo()));
		}
	}
	
	public void validarUnicoRegistro(String registro) {
		if (repository.existePeloRegistro(registro)) {
			throw new ErroDeValidacaoDeMatriculaException(String.format("Registro de matrícula %s já existe na base de dados", registro));
		} 
	}
	
	public void validarAlunoNaoEvadidoNaTurma(Turma turma, Aluno aluno) {
		if(repository.buscarPelaTurmaEAluno(turma, aluno).stream().anyMatch(matricula -> matricula.getStatus() == MatriculaStatus.ALUNO_EVADIDO)) {
            throw new ErroDeValidacaoDeMatriculaException("Aluno não pode ser matriculado, pois está evadido em um módulo da mesma turma");
		}
	}
	
}
