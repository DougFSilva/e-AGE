package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.util.ArrayList;
import java.util.List;

import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.MatriculaAlunoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.MatriculaAlunosForm;
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
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(form.alunoID());
		validar(modulo, aluno, form.registro());
		Matricula matricula = new Matricula(form.registro(), modulo, aluno, form.dataDaMatricula(), MatriculaStatus.MATRICULA_ATIVA);
		return MatriculaResposta.deMatricula(repository.salvar(matricula));
	}
	
	public List<Matricula> matricular(MatriculaAlunosForm forms){
		List<Matricula> matriculas = new ArrayList<>();
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(forms.moduloID());
		forms.alunos().forEach(form -> {
			Aluno aluno = alunoRepository.buscarPeloIDOuThrow(form.alunoID());
			validar(modulo, aluno, form.registro());
			matriculas.add(new Matricula(form.registro(), modulo, aluno, form.dataDaMatricula(), MatriculaStatus.MATRICULA_ATIVA));
		});
		return repository.salvarTodas(matriculas);
	}
	
	private void validar(Modulo modulo, Aluno aluno, String registro) {
		validador.validarUnicoAlunoPorModulo(modulo, aluno);
		validador.validarUnicoRegistro(registro);
		validador.validarAlunoNaoEvadidoNaTurma(modulo.getTurma(), aluno);
	}
	
}
