package com.dougfsilva.e_AGE.aplicacao.casosdeuso.ocorrencia;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.aplicacao.formulario.AbreOcorrenciaForm;
import com.dougfsilva.e_AGE.dominio.configuracao.ChaveDeConfiguracao;
import com.dougfsilva.e_AGE.dominio.configuracao.ConfiguracaoRepository;
import com.dougfsilva.e_AGE.dominio.ocorrencia.Ocorrencia;
import com.dougfsilva.e_AGE.dominio.ocorrencia.OcorrenciaRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.utilidades.notificacao.EnviadorDeEmail;
import com.dougfsilva.e_AGE.dominio.utilidades.notificacao.EnviadorDeMensagemDeCelular;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbreOcorrencia {

	private final OcorrenciaRepository repository;
	private final FuncionarioRepository funcionarioRepository;
	private final AlunoRepository alunoRepository;
	private final EnviaNotificacaoDeOcorrencia notificador;
	
	public Ocorrencia abrir(AbreOcorrenciaForm form) {
		Funcionario relator = funcionarioRepository.buscarPeloIDOuThrow(form.relatorID());
		Aluno aluno = alunoRepository.buscarPeloIDOuThrow(form.alunoID());
		Ocorrencia ocorrencia = new Ocorrencia(LocalDateTime.now(), relator, aluno, form.tipo(), form.encaminhada(), form.restrita(), form.descricao());
		Ocorrencia ocorrenciaSalva = repository.salvar(ocorrencia);
		notificador.
	}
}
