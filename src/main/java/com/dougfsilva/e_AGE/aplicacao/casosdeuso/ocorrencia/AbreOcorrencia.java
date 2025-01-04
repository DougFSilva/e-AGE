package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

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
		Ocorrencia ocorrencia = new Ocorrencia(LocalDateTime.now(), relator, matricula, form.tipo(), form.encaminhada(), form.restrita(), form.descricao());
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notifica.aoAbrirOcorrencia(ocorrenciaSalva);
		return ocorrenciaSalva;
	}

	private Funcionario buscarFuncionarioAutenticado() {
		return buscaFuncionario.buscarPeloUsuarioAutenticado(); 
	}
}
