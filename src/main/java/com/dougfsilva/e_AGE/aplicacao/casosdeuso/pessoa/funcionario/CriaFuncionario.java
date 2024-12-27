package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.funcionario;

import java.util.Arrays;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.endereco.CriaEndereco;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.ValidaPessoa;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario.CriaUsuario;
import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaFuncionarioForm;
import com.dougfsilva.e_AGE.aplicacao.dto.resposta.FuncionarioResposta;
import com.dougfsilva.e_AGE.dominio.endereco.Endereco;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComFuncionarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDePessoaException;
import com.dougfsilva.e_AGE.dominio.exception.ObjetoNaoEncontradoException;
import com.dougfsilva.e_AGE.dominio.pessoa.Email;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.Funcionario;
import com.dougfsilva.e_AGE.dominio.pessoa.funcionario.FuncionarioRepository;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.ImagemService;
import com.dougfsilva.e_AGE.dominio.utilidades.imagem.TipoImagem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaFuncionario {

	private final FuncionarioRepository repository;
	private final ImagemService imagemService;
	private final CriaEndereco criaEndereco;
	private final CriaUsuario criaUsuario;
	private final ValidaPessoa validador;
	private final LogPadrao log;
	
	public FuncionarioResposta criar(CriaFuncionarioForm form) {
		try {
			validador.validarUnicoRG(form.RG());
			Funcionario funcionario = construirFuncionario(form);
			Funcionario funcionarioSalvo = repository.salvar(funcionario);
			log.info(String.format("Criado funcionário %s com ID %s", funcionarioSalvo.getNome(), funcionarioSalvo.getID()));
			return FuncionarioResposta.deFuncionario(funcionarioSalvo);
		} catch (ErroDeValidacaoDePessoaException | ObjetoNaoEncontradoException | ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar funcionário %s : %s", form.nome(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar funcionário %s : %s", form.nome(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		}
	}
	
	public FuncionarioResposta criarComUsuario(CriaFuncionarioForm form) {
		try {
			validador.validarUnicoRG(form.RG());
			Funcionario funcionario = construirFuncionario(form);
			Usuario usuario = criaUsuario.criarUsuarioDefaultParaPessoa(funcionario, Arrays.asList(TipoPerfil.FUNCIONARIO));
			funcionario.setUsuario(usuario);
			Funcionario funcionarioSalvo = repository.salvar(funcionario);
			log.info(String.format("Criado funcionário %s com ID %s", funcionarioSalvo.getNome(), funcionarioSalvo.getID()));
			return FuncionarioResposta.deFuncionario(funcionarioSalvo);
		} catch (ErroDeValidacaoDePessoaException | ObjetoNaoEncontradoException | ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar funcionário %s : %s", form.nome(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar funcionário %s : %s", form.nome(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComFuncionarioException(mensagem, e);
		}
	}
	
	private Funcionario construirFuncionario(CriaFuncionarioForm form) {
		Endereco endereco = criaEndereco.criar(form.endereco());
		Funcionario funcionario = new Funcionario(
				form.nome(), 
				form.sexo(), 
				form.RG(), 
				form.telefone(), 
				new Email(form.email()), 
				form.dataDeNascimento(), 
				endereco, 
				imagemService.buscarImagemPadrao(TipoImagem.ALUNO),
				form.registro(),
				form.cargo());
		return funcionario;
	}
}
