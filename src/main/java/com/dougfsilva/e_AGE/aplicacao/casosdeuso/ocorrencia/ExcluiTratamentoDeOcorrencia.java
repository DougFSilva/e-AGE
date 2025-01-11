package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.ocorrencia.TratamentoDeOcorrencia;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluiTratamentoDeOcorrencia {

	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	
	public Ocorrencia excluir(String ocorrenciaID, String tratamentoID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ocorrenciaID);
		garantirOcorrenciaAberta(ocorrencia);
		TratamentoDeOcorrencia tratamentoDeOcorrencia = filtrarTratamento(ocorrencia, tratamentoID);
		validarPermissaoDeUsuario(tratamentoDeOcorrencia);
		ocorrencia.removeTratamento(tratamentoDeOcorrencia);
		return repository.salvar(ocorrencia);
	}
	
	private void garantirOcorrenciaAberta(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.ABERTA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Só é possível excluir tratamentos de uma ocorrência aberta",
							ocorrencia.getID(), ocorrencia.getStatus().name().toLowerCase()));
		}
	}
	
	private TratamentoDeOcorrencia filtrarTratamento(Ocorrencia ocorrencia, String tratamentoID) {
		return ocorrencia.getTratamentos()
				.stream()
				.filter(tratamento -> tratamento.getID().equals(tratamentoID))
				.findFirst().orElseThrow(() -> 
					new ObjetoNaoEncontradoException(
							String.format("Tratamento de ocorrência com ID %s não encontrado para ocorrência %s", tratamentoID, ocorrencia.getID())));
	}
	
	private void validarPermissaoDeUsuario(TratamentoDeOcorrencia tratamento) {
		Funcionario funcionario = buscaFuncionario.buscarPeloUsuarioAutenticado();
		if (!tratamento.getTratador().equals(funcionario)) {
			throw new ErroDeValidacaoDeOcorrenciaException("Somente o tratador da ocorrência pode excluí-la");
		}
	}
}
