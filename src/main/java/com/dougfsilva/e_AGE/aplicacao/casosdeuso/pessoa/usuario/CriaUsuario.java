package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario;

import java.util.List;

import com.dougfsilva.e_AGE.aplicacao.casosdeuso.utilidades.LogPadrao;
import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaUsuarioForm;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeOperacaoComUsuarioException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeUsuarioException;
import com.dougfsilva.e_AGE.dominio.pessoa.Pessoa;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.CodificadorDeSenha;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.SenhaDeUsuario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriaUsuario {
	
	private final UsuarioRepository repository;
	private final CodificadorDeSenha codificador;
	private final ValidaUsuario validador;
	private final LogPadrao log;
	
	public Usuario criarUsuario(CriaUsuarioForm form) {
		try {
			validador.validarUnicoNomeDeUsuario(form.nomeDeUsuario());
			SenhaDeUsuario senha = new SenhaDeUsuario(form.senha(), codificador);
			Usuario usuario = new Usuario(form.nomeDeUsuario(), senha);
			form.tiposPerfis().forEach(tipo -> usuario.adicionarPerfil(tipo));
			Usuario usuarioSalvo = repository.salvar(usuario);
			log.info(String.format("Criado usuário %s", usuarioSalvo.getNomeDeUsuario()));
			return usuarioSalvo;
		} catch (ErroDeValidacaoDeUsuarioException | ErroDeValidacaoDeCamposException e) {
			String mensagem = String.format("Erro ao criar usuário %s : %s", form.nomeDeUsuario(), e.getMessage());
			log.warn(mensagem, e);
			throw new ErroDeOperacaoComUsuarioException(mensagem, e);
		} catch (Exception e) {
			String mensagem = String.format("Erro inesperado ao criar usuário %s : %s", form.nomeDeUsuario(), e.getMessage());
			log.error(mensagem, e);
			throw new ErroDeOperacaoComUsuarioException(mensagem, e);
		}
	}
	
	public Usuario criarUsuarioDefaultParaPessoa(Pessoa pessoa, List<TipoPerfil> tiposPerfis) {
			String nomeDeUsuario = pessoa.getEmail().getEndereco();
			String senha = gerarSenhaParaPessoa(pessoa);
			CriaUsuarioForm form = new CriaUsuarioForm(nomeDeUsuario, senha, tiposPerfis);
			return criarUsuario(form);
	}
	
	private String gerarSenhaParaPessoa(Pessoa pessoa) {
		String primeiros4DigitosDoRG = pessoa.getRG().substring(0, 4);
		return "Pw" + primeiros4DigitosDoRG + "@";
	}
}