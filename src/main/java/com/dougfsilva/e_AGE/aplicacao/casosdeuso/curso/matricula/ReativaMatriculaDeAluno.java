package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.matricula;

import java.util.Optional;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.MatriculaResposta;
import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReativaMatriculaDeAluno {

	private final MatriculaRepository repository;
	private final EvasaoRepository evasaoRepository;
	
	public MatriculaResposta reativarPeloID(String ID) {
		Matricula matricula = repository.buscarPeloIDOuThrow(ID);
		excluirEvasao(matricula);
		matricula.setStatus(MatriculaStatus.MATRICULA_ATIVA);
		return MatriculaResposta.deMatricula(repository.salvar(matricula));
	}
	
	private void excluirEvasao(Matricula matricula) {
		if (matricula.getStatus() == MatriculaStatus.ALUNO_EVADIDO) {
			Optional<Evasao> evasao = evasaoRepository.buscarPelaMatricula(matricula);
			if (evasao.isPresent()) {
				evasaoRepository.excluir(evasao.get());
			}
		}
	}
}
