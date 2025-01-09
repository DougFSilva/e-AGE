package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.aplicacao.formulario.AbreOcorrenciaForm;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEvasaoException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.AssinaturaDeOcorrenciaResponsavel;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.PINService;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbreOcorrencia {

	private final OcorrenciaRepository repository;
	private final MatriculaRepository matriculaRepository;
	private final ModuloRepository moduloRepository;
	private final PINService pinService;
	private final BuscaFuncionario buscaFuncionario;
	private final EnviaNotificacaoDeOcorrencia notifica;

	public Ocorrencia abrir(AbreOcorrenciaForm form) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(form.matriculaID());
		Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
		garantirModuloPertencenteATurma(modulo, matricula.getTurma());
		Funcionario relator = buscaFuncionario.buscarPeloUsuarioAutenticado();
		Ocorrencia ocorrencia = new Ocorrencia(LocalDateTime.now(), relator, matricula, modulo, form.tipo(),
				form.encaminhada(), form.restrita(), form.descricao());
		if (ocorrencia.getRestrita()) {
			ocorrencia.setEncaminhada(true);
		}
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		return notificar(ocorrenciaSalva);
	}

	private void garantirModuloPertencenteATurma(Modulo modulo, Turma turma) {
		if (!modulo.getTurma().equals(turma)) {
			throw new ErroDeValidacaoDeEvasaoException(
					String.format("Não é possível abrir a ocorrência pois o módulo %s não pertence a turma %s",
							modulo.getCodigo(), turma.getCodigo()));
		}
	}

	private Ocorrencia notificar(Ocorrencia ocorrencia) {
		boolean encaminhada = ocorrencia.getEncaminhada();
		boolean alunoMenorDeIdade = ocorrencia.getMatricula().getAluno().menorDeIdade();
		if (!encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAluno(ocorrencia, OperacaoDeOcorrencia.ABERTA);
		} else if (encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestores(ocorrencia, OperacaoDeOcorrencia.ABERTA);
		} else if (!encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaResponsavel(ocorrencia, OperacaoDeOcorrencia.ABERTA);
		} else {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestores(ocorrencia, OperacaoDeOcorrencia.ABERTA);
			return notificarResponsavelComPIN(ocorrencia);
		}
		return ocorrencia;
	}

	private Ocorrencia notificarResponsavelComPIN(Ocorrencia ocorrencia) {
		String PIN = pinService.gerarPIN();
		String PINCodificado = pinService.codificar(PIN);
		ocorrencia.setAssinaturaResponsavel(new AssinaturaDeOcorrenciaResponsavel(PINCodificado));
		Ocorrencia ocorrenciaAtualizadaComPIN = repository.salvar(ocorrencia);
		notifica.enviarNotificacaoParaResponsavelComPIN(ocorrenciaAtualizadaComPIN, OperacaoDeOcorrencia.ABERTA, PIN);
		return ocorrenciaAtualizadaComPIN;
	}

}
