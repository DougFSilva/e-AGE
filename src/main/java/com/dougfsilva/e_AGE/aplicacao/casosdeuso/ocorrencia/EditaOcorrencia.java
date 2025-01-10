package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.aplicacao.formulario.EditaOcorrenciaForm;
import com.dougfsilva.e_AGE.dominio.curso.modulo.Modulo;
import com.dougfsilva.e_AGE.dominio.curso.modulo.ModuloRepository;
import com.dougfsilva.e_AGE.dominio.curso.turma.Turma;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeEvasaoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeOcorrenciaException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.AssinaturaDeOcorrenciaResponsavel;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaStatus;
import com.dougfsilva.e_AGE.dominio.ocorrencia.PINService;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditaOcorrencia {

	private final OcorrenciaRepository repository;
	private final ModuloRepository moduloRepository;
	private final PINService pinService;
	private final BuscaFuncionario buscaFuncionario;
	private final EnviaNotificacaoDeOcorrencia notifica;
	
	public Ocorrencia editar(EditaOcorrenciaForm form) {
		Ocorrencia ocorrencia = repository.buscarPeloIDOuThrow(form.ID());
		validarPermissoesDeUsuario(ocorrencia);
		garantirOcorrenciaAberta(ocorrencia);
		garantirOcorrenciaSemTratamento(ocorrencia);
		Ocorrencia ocorrenciaEditada = editarDados(ocorrencia, form);
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrenciaEditada);
		notificar(ocorrenciaSalva);
		return ocorrenciaSalva;
	}
	
	private void validarPermissoesDeUsuario(Ocorrencia ocorrencia) {
		Funcionario funcionarioAutenticado = buscaFuncionario.buscarPeloUsuarioAutenticado();
	    boolean usuarioRelator = ocorrencia.getAberturaDeOcorrencia().getRelator().equals(funcionarioAutenticado);
	    if (!usuarioRelator) {
	    	throw new ErroDeValidacaoDeOcorrenciaException(
		            String.format("Apenas o relator pode editar a ocorrência", ocorrencia.getID()));
	    }
	}
	
	private void garantirOcorrenciaAberta(Ocorrencia ocorrencia) {
		if (ocorrencia.getStatus() != OcorrenciaStatus.ABERTA) {
			throw new ErroDeValidacaoDeOcorrenciaException(
					String.format("A ocorrência %s está %s. Somente uma ocorrência aberta pode ser editada",
							ocorrencia.getID(), ocorrencia.getStatus().name().toLowerCase()));
		}
	}
	
	private void garantirOcorrenciaSemTratamento(Ocorrencia ocorrencia) {
		if (ocorrencia.getTratamento().size() > 0) {
			throw new ErroDeValidacaoDeOcorrenciaException("Somente ocorrência sem tratamento pode ser editada");
		}
	}
	
	private Ocorrencia editarDados(Ocorrencia ocorrencia, EditaOcorrenciaForm form) {
		if (form.moduloID() != null && !form.moduloID().isBlank() && !form.moduloID().equals(ocorrencia.getModulo().getID())) {
			Modulo modulo = moduloRepository.buscarPeloIDOuThrow(form.moduloID());
			garantirModuloPertencenteATurma(modulo, ocorrencia.getMatricula().getTurma());
			ocorrencia.setModulo(modulo);
		}
		if (form.tipo() != null) {
			ocorrencia.setTipo(form.tipo());
		}
		if (form.encaminhada() != null) {
			ocorrencia.setEncaminhada(form.encaminhada());
		}
		if (form.restrita() != null) {
			ocorrencia.setRestrita(form.restrita());
		}
		if (form.descricao() != null && !form.descricao().isBlank()) {
			ocorrencia.setDescricao(form.descricao());
		}
		return ocorrencia;
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
			notifica.enviarNotificacaoParaAluno(ocorrencia, OperacaoDeOcorrencia.ATUALIZADA);
		} else if (encaminhada && !alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestores(ocorrencia, OperacaoDeOcorrencia.ATUALIZADA);
		} else if (!encaminhada && alunoMenorDeIdade) {
			notifica.enviarNotificacaoParaAlunoComCopiaParaResponsavel(ocorrencia, OperacaoDeOcorrencia.ATUALIZADA);
		} else {
			notifica.enviarNotificacaoParaAlunoComCopiaParaGestores(ocorrencia, OperacaoDeOcorrencia.ATUALIZADA);
			return notificarResponsavelComPIN(ocorrencia);
		}
		return ocorrencia;
	}
	
	private Ocorrencia notificarResponsavelComPIN(Ocorrencia ocorrencia) {
		if (ocorrencia.getAssinaturaResponsavel() == null) {
			String PIN = pinService.gerarPIN();
			String PINCodificado = pinService.codificar(PIN);
			ocorrencia.setAssinaturaResponsavel(new AssinaturaDeOcorrenciaResponsavel(PINCodificado));
			Ocorrencia ocorrenciaAtualizadaComPIN = repository.salvar(ocorrencia);
			notifica.enviarNotificacaoParaResponsavelComPIN(ocorrenciaAtualizadaComPIN, OperacaoDeOcorrencia.ATUALIZADA, PIN);
			return ocorrenciaAtualizadaComPIN;
		} else {
			notifica.enviarNotificacaoParaResponsavelComPIN(ocorrencia, OperacaoDeOcorrencia.ATUALIZADA, ocorrencia.getAssinaturaResponsavel().getPIN());
			return ocorrencia;
		}
	}
}
