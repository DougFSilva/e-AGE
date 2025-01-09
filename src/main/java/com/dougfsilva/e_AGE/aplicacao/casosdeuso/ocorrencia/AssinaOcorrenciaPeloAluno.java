package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.aplicacao.formulario.AssinaOcorrenciaPeloAlunoForm;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.AssinaturaDeOcorrenciaAluno;
import com.dougfsilva.e_AGE.dominio.ocorrencia.ChaveSecreta;
import com.dougfsilva.e_AGE.dominio.ocorrencia.CodificadorDeAssinatura;
import com.dougfsilva.e_AGE.dominio.ocorrencia.GeradorDeSalt;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssinaOcorrenciaPeloAluno {
	
	private final OcorrenciaRepository repository;
	private final CodificadorDeAssinatura codificadorDeAssinatura;
	private final GeradorDeSalt geradorDeSalt;
	private final ChaveSecreta chaveSecreta;
	private final BuscaFuncionario buscaFuncionario;

	public Ocorrencia assinar(AssinaOcorrenciaPeloAlunoForm form) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(form.ocorrenciaID());
		garantirOcorrenciaFechada(ocorrencia);
		Funcionario funcionarioAutenticado = buscaFuncionario.buscarPeloUsuarioAutenticado();
		validarPermissoesDeUsuario(ocorrencia, funcionarioAutenticado);
		AssinaturaDeOcorrenciaAluno assinatura = gerarAssinaturaCodificada(ocorrencia, form.assinatura(), funcionarioAutenticado);
		ocorrencia.setAssinaturaAluno(assinatura);
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		return ocorrenciaSalva;
	}
	
	private void garantirOcorrenciaFechada(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.FECHADA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência fechada pode ser assinada pelo aluno",
							ocorrencia.getID(), ocorrencia.getStatus().name().toLowerCase()));
		}
	}
	
	private void validarPermissoesDeUsuario(Ocorrencia ocorrencia, Funcionario funcionarioAutenticado) {
	    boolean usuarioGestor = funcionarioAutenticado.getUsuario().contemPerfil(TipoPerfil.GESTOR);
	    if (ocorrencia.getEncaminhada() && !usuarioGestor) {
	        throw new ErroDeValidacaoDeOcorrenciaException(
	            "Somente usuário com perfil gestor pode coletar assinatura do aluno de uma ocorrência encaminhada");
	    }
	}
	
	private AssinaturaDeOcorrenciaAluno gerarAssinaturaCodificada(Ocorrencia ocorrencia, String assinatura, Funcionario funcionarioAutenticado) {
		String salt = geradorDeSalt.gerar();
		LocalDateTime timestamp = LocalDateTime.now();
		String dadosParaHash = ocorrencia.getID() + assinatura + timestamp + salt + chaveSecreta.buscarChave();
		String assinaturaCodificada = codificadorDeAssinatura.codificar(dadosParaHash);
		return new AssinaturaDeOcorrenciaAluno(assinaturaCodificada, timestamp, funcionarioAutenticado, salt);
	}
	
}
