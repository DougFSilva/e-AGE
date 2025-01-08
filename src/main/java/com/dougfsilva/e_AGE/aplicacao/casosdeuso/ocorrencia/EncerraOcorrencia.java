package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EncerraOcorrencia {

	private OcorrenciaRepository repository;
	private BuscaFuncionario buscaFuncionario;
	private final EnviaNotificacaoDeOcorrencia notifica;
	
	public Ocorrencia encerrarPeloID(String ID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ID);
		Funcionario funcionario = buscaFuncionario.buscarPeloUsuarioAutenticado();
		garantirUsuarioGestorSeOcorrenciaForEncaminhada(ocorrencia, funcionario);
		garantirOcorrenciaAssinada(ocorrencia);
		ocorrencia.setStatus(OcorrenciaStatus.ENCERRADA);
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notificar(ocorrenciaSalva);
		return ocorrenciaSalva;
	}
	
	private void notificar(Ocorrencia ocorrencia) {
		Boolean encaminhada = ocorrencia.getEncaminhada();
		Boolean alunoMenorDeIdade = ocorrencia.getMatricula().getAluno().menorDeIdade();
		if (!encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAluno(ocorrencia, OperacaoDeOcorrencia.ENCERRADA);
		} else if (encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestores(ocorrencia, OperacaoDeOcorrencia.ENCERRADA);
		} else if (!encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaResponsavel(ocorrencia, OperacaoDeOcorrencia.ENCERRADA);
		} else if (encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestoresEResponsavel(ocorrencia, OperacaoDeOcorrencia.ENCERRADA);
		}
	}
	
	private void garantirUsuarioGestorSeOcorrenciaForEncaminhada(Ocorrencia ocorrencia, Funcionario funcionarioAutenticado) {
		if (ocorrencia.getEncaminhada() && !funcionarioAutenticado.getUsuario().contemPerfil(TipoPerfil.GESTOR)) {
			throw new ErroDeValidacaoDeOcorrenciaException("Uma ocorrência encaminhada só pode ser encerrada por um usuário gestor");
		}
	}
	
	private void garantirOcorrenciaAssinada(Ocorrencia ocorrencia) {
		if (!ocorrencia.getAssinaturaAluno().assinada()) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("Não é possível encerrar a ocorrência %s porque ainda não foi assinada pelo aluno", ocorrencia.getID()));
		}
		if (ocorrencia.getMatricula().getAluno().menorDeIdade() && !ocorrencia.getAssinaturaResponsavel().assinada()) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("Não é possível encerrar a ocorrência %s porque ainda não foi assinada pelo responsável", ocorrencia.getID()));
		}
	}
	
}
