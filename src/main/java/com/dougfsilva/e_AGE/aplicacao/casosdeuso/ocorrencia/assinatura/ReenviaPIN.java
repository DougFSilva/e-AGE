package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia.assinatura;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia.EnviaNotificacaoDeOcorrencia;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia.OperacaoDeOcorrencia;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.ocorrencia.assinatura.PINService;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReenviaPIN {

	private final OcorrenciaRepository repository;
	private final PINService pinService;
	private final BuscaFuncionario buscaFuncionario;
	private final EnviaNotificacaoDeOcorrencia notifica;

	public Ocorrencia reenviar(String ocorrenciaID) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(ocorrenciaID);
		garantirOcorrenciaNaoEncerrada(ocorrencia);
		garantirOCorrenciaEncaminhaEAlunoMenor(ocorrencia);
		Funcionario funcionarioAutenticado = buscaFuncionario.buscarPeloUsuarioAutenticado();
		validarPermissoesDeUsuario(ocorrencia, funcionarioAutenticado);
		String PIN = pinService.gerarPIN();
		String PINCodificado = pinService.codificar(PIN);
		ocorrencia.getAssinaturaResponsavel().setPIN(PINCodificado);
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notificar(ocorrenciaSalva, PIN);
		return ocorrenciaSalva;
	}

	private void garantirOcorrenciaNaoEncerrada(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() == OcorrenciaStatus.ENCERRADA) {
			throw new ErroDeValidacaoDeOcorrenciaException(String
					.format("Não é possível reenviar o PIN porque a ocorrência %s está encerrada", ocorrencia.getID()));
		}
	}
	
	private void garantirOCorrenciaEncaminhaEAlunoMenor(Ocorrencia ocorrencia) {
		boolean alunoMenor = ocorrencia.getMatricula().getAluno().menorDeIdade();
		boolean encaminhada = ocorrencia.getEncaminhada();
		if (!alunoMenor || !encaminhada) {
			throw new ErroDeValidacaoDeOcorrenciaException("Só é possível gerar um PIN para uma ocorrência encaminhada de um aluno menor de idade");
		}
	}

	private void validarPermissoesDeUsuario(Ocorrencia ocorrencia, Funcionario funcionarioAutenticado) {
		boolean usuarioGestor = funcionarioAutenticado.getUsuario().contemPerfil(TipoPerfil.GESTOR);
		if (!usuarioGestor) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					"Somente usuário com perfil Gestor pode solicitar o reenvio de PIN");
		}
	}
	
	private void notificar(Ocorrencia ocorrencia, String PIN) {
		notifica.enviarNotificacaoParaResponsavelComPIN(ocorrencia, OperacaoDeOcorrencia.NOVO_PIN_GERADO, PIN);
	}
}
