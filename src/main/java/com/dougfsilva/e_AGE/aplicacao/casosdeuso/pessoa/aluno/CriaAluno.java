package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.aluno;

import java.util.Arrays;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.CriaEndereco;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.ValidaPessoa;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario.CriaUsuario;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaAlunoForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.AlunoResposta;
import com.dougfsilva.e_AGE.dominio.empresa.Empresa;
import com.dougfsilva.e_AGE.dominio.empresa.EmpresaRepository;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComAlunoException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDePessoaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.Aluno;
import com.dougfsilva.e_AGE.dominio.pessoa.aluno.AlunoRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaAluno {

	private final AlunoRepository repository;
	private final EmpresaRepository  empresaRepository;
	private final ImagemService imagemService;
	private final CriaEndereco criaEndereco;
	private final CriaUsuario criaUsuario;
	private final ValidaPessoa validador;
	private final LogPadrao log;
	
	public AlunoResposta criar(CriaAlunoForm form) {
		try {
			validador.validaUnicoRG(form.RG());
			Aluno aluno = construirAluno(form);
			Aluno alunoSalvo = repository.salvar(aluno);
			log.info(String.format("Criado aluno %s com ID %s ", alunoSalvo.getNome(), alunoSalvo.getID()));
			return AlunoResposta.deAluno(alunoSalvo);
		} catch (ErroDeValidacaoDePessoaException | ObjetoNaoEncontradoException 
				| ErroDeValidacaoDeCamposException | ErroDeOperacaoComUsuarioException  e) {
			String mensagem = String.format("Erro ao criar aluno %s : %s", form.nome(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar aluno %s : %s", form.nome(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComAlunoException(mensagem, e);
		}
	}
	
	private Aluno construirAluno(CriaAlunoForm form) {
		Endereco endereco = criaEndereco.criar(form.endereco());
		Aluno aluno = new Aluno(
				form.nome(), 
				form.sexo(), 
				form.RG(), 
				form.telefone(), 
				new Email(form.email()), 
				form.dataDeNascimento(), 
				endereco, 
				imagemService.buscarImagemPadrao(TipoImagem.ALUNO));
		if (form.empresaID() != null && !form.empresaID().isBlank()) {
			Empresa empresa = empresaRepository.buscarPeloIDOuThrow(form.empresaID());
			aluno.setEmpresa(empresa);
		}
		if (form.responsavel() != null) {
			aluno.setResponsavel(form.responsavel());
		}
		if (form.criarUsuario()) {
			Usuario usuario = criaUsuario.criarUsuarioDefaultParaPessoa(aluno, Arrays.asList(TipoPerfil.ALUNO));
			aluno.setUsuario(usuario);
		}
		return aluno;
	}
}
