package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.aplicacao.formulario.AssinaOcorrenciaPeloResponsavelForm;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.ChaveSecreta;
import com.dougfsilva.e_AGE.dominio.ocorrencia.CodificadorDeAssinatura;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.ocorrencia.PINService;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssinaOcorrenciaPeloResponsavel {

	private final OcorrenciaRepository repository;
	private final PINService pinService;
	private final CodificadorDeAssinatura codificadorDeAssinatura;
	private final ChaveSecreta chaveSecreta;
	private final BuscaFuncionario buscaFuncionario;

	public Ocorrencia assinarOcorrenciaPeloID(AssinaOcorrenciaPeloResponsavelForm form) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(form.ocorrenciaID());
		garantirOcorrenciaFechada(ocorrencia);
		garantirAlunoMenorDeIdade(ocorrencia.getMatricula().getAluno());
		Funcionario funcionarioAutenticado = buscaFuncionario.buscarPeloUsuarioAutenticado();
		validarPermissoesDeUsuario(ocorrencia, funcionarioAutenticado);
		validarPIN(ocorrencia, form.PIN());
		Ocorrencia ocorrenciaAssinadaEAtualizada = gerarAssinaturaEAtualizarOcorrencia(ocorrencia, form.PIN(), funcionarioAutenticado);
		return repository.salvar(ocorrenciaAssinadaEAtualizada);
	}
	
	private void garantirOcorrenciaFechada(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.FECHADA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência fechada pode ser assinada pelo responsável",
							ocorrencia.getID(), ocorrencia.getStatus().name().toLowerCase()));
		}
	}

	private void garantirAlunoMenorDeIdade(Aluno aluno) {
		if (!aluno.menorDeIdade()) {
			throw new ErroDeValidacaoDeOcorrenciaException(String.format(
					"Aluno %s é maior de idade, ele mesmo deve assinar a ocorrência, sem a necessidade de assinatura por parte do responsável",
					aluno.getNome()));
		}
	}
	
	private void validarPermissoesDeUsuario(Ocorrencia ocorrencia, Funcionario funcionarioAutenticado) {
	    boolean usuarioGestor = funcionarioAutenticado.getUsuario().contemPerfil(TipoPerfil.GESTOR);
	    if (ocorrencia.getEncaminhada() && !usuarioGestor) {
	        throw new ErroDeValidacaoDeOcorrenciaException(
	            "Somente usuário com perfil gestor pode coletar assinatura do responsável de uma ocorrência encaminhada");
	    }
	}

	private void validarPIN(Ocorrencia ocorrencia, String PIN) {
		if (!pinService.comparar(PIN, ocorrencia.getAssinaturaResponsavel().getPIN())) {
			throw new ErroDeValidacaoDeOcorrenciaException(String.format("O PIN fornecido é inválido"));
		}
	}

	private Ocorrencia gerarAssinaturaEAtualizarOcorrencia(Ocorrencia ocorrencia, String PIN, Funcionario funcionarioAutenticado) {
		LocalDateTime timestamp = LocalDateTime.now();
		String dadosParaHash = ocorrencia.getID() + timestamp + PIN + chaveSecreta.buscarChave();
		String assinatura = codificadorDeAssinatura.codificar(dadosParaHash);
		ocorrencia.getAssinaturaResponsavel().setResponsavelPelaColetaDaAssinatura(funcionarioAutenticado);
		ocorrencia.getAssinaturaResponsavel().setTimestamp(timestamp);
		ocorrencia.getAssinaturaResponsavel().setAssinatura(assinatura);
		return ocorrencia;
	}
	
}
