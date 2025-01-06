package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;
import java.util.Random;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario.BuscaFuncionario;
import com.dougfsilva.e_AGE.aplicacao.formulario.AbreOcorrenciaForm;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbreOcorrencia {

	private final OcorrenciaRepository repository;
	private final BuscaFuncionario buscaFuncionario;
	private final MatriculaRepository matriculaRepository;
	private final EnviaNotificacaoDeOcorrencia notifica;

	public Ocorrencia abrir(AbreOcorrenciaForm form) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(form.matriculaID());
		Funcionario relator = buscarFuncionarioAutenticado();
		Ocorrencia ocorrencia = new Ocorrencia(LocalDateTime.now(), relator, matricula, form.tipo(), form.encaminhada(),
				form.restrita(), form.descricao());
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notificar(ocorrencia);
		return ocorrenciaSalva;
	}

	private Funcionario buscarFuncionarioAutenticado() {
		return buscaFuncionario.buscarPeloUsuarioAutenticado();
	}

	private void notificar(Ocorrencia ocorrencia) {
		Boolean encaminhada = ocorrencia.getEncaminhada();
		Boolean alunoMenorDeIdade = ocorrencia.getMatricula().getAluno().calcularIdade() < 18;
		if (!encaminhada && !alunoMenorDeIdade) {
			notifica.aoAbrirOcorrencia(ocorrencia);
		} else if (encaminhada && !alunoMenorDeIdade) {
			notifica.aoAbrirOcorrenciaComCopiaParaGestores(ocorrencia);
		} else if (!encaminhada && alunoMenorDeIdade) {
			notifica.aoAbrirOcorrenciaComCopiaParaResponsavel(ocorrencia);
		} else if (encaminhada && alunoMenorDeIdade) {
			notifica.aoAbrirOcorrenciaComCopiaParaGestores(ocorrencia);
			notifica.aoAbrirOcorrenciaParaResponsavelComPIN(ocorrencia, gerarPIN());
		}
	}

	private String gerarPIN() {
		Random random = new Random();
		return String.format("%06d", random.nextInt(1000000));
	}
}
