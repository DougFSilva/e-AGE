package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.ocorrencia.TratamentoDeOcorrencia;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrataOcorrencia {

	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	private final EnviaNotificacaoDeOcorrencia notifica;

	public Ocorrencia tratar(String ID, String tratamento) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
		garantirOcorrenciaAberta(ocorrencia);
		ocorrencia.addTratamento(new TratamentoDeOcorrencia(buscarFuncionarioAutenticado(), LocalDateTime.now(), tratamento));
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notifica.aoAtualizarOcorrencia(ocorrenciaSalva);
		return ocorrenciaSalva;
	}

	private void garantirOcorrenciaAberta(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.ABERTA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência aberta pode ser tratada",
							ocorrencia.getID(), ocorrencia.getStatus().name()));
		}
	}

	private Funcionario buscarFuncionarioAutenticado() {
		return buscaFuncionario.buscarPeloUsuarioAutenticado();
	}
}
