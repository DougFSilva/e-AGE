package com.dougfsilva.e_AGE.aplicacao.casosdeuso.curso.evasao;

import com.dougfsilva.e_AGE.aplicacao.dto.resposta.EvasaoResposta;
import com.dougfsilva.e_AGE.dominio.curso.evasao.Evasao;
import com.dougfsilva.e_AGE.dominio.curso.evasao.EvasaoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaEvasao {

	private final EvasaoRepository repository;
	
	public EvasaoResposta editar(String ID, String motivo) {
		Evasao evasao = repository.buscarPeloIDOuThrow(ID);
		if (motivo != null & !motivo.isBlank()) {
			evasao.setMotivo(motivo);
		}
		return EvasaoResposta.deEvasao(repository.salvar(evasao));
	}
}
