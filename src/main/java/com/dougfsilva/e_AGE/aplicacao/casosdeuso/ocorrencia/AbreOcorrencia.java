package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.aplicacao.formulario.AbreOcorrenciaForm;
import com.dougfsilva.e_AGE.dominio.curso.matricula.Matricula;
import com.dougfsilva.e_AGE.dominio.curso.matricula.MatriculaRepository;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.UsuarioAutenticado;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbreOcorrencia {

	private final OcorrenciaRepository repository;
	private final FuncionarioRepository funcionarioRepository;
	private final MatriculaRepository matriculaRepository;
	private final UsuarioAutenticado usuarioAutenticado;
	private final EnviaNotificacaoDeOcorrencia notificador;

	public Ocorrencia abrir(AbreOcorrenciaForm form) {
		Matricula matricula = matriculaRepository.buscarPeloIDOuThrow(form.matriculaID());
		Funcionario relator = buscarRelatorPeloUsuarioAutenticado();
		Ocorrencia ocorrencia = new Ocorrencia(LocalDateTime.now(), relator, matricula, form.tipo(), form.encaminhada(), form.restrita(), form.descricao());
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notificador.aoAbrirOcorrencia(ocorrenciaSalva);
		return ocorrenciaSalva;
	}

	private Funcionario buscarRelatorPeloUsuarioAutenticado() {
		return funcionarioRepository.buscarPeloUsuario(usuarioAutenticado.buscarUsuarioAtualOuThrow()).orElseThrow(
				() -> new ObjetoNaoEncontradoException("Funcionário não encontrado pelo usuário autenticado"));
	}
}
