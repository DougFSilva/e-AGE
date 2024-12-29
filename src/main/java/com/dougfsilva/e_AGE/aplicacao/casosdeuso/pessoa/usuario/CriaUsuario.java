package com.dougfsilva.e_AGE.aplicacao.casosdeuso.pessoa.usuario;

import java.util.List;

import com.dougfsilva.e_AGE.aplicacao.dto.requisicao.CriaUsuarioForm;
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
	
	public Usuario criarUsuario(CriaUsuarioForm form) {
		validador.validarUnicoNomeDeUsuario(form.nomeDeUsuario());
		SenhaDeUsuario senha = new SenhaDeUsuario(form.senha(), codificador);
		Usuario usuario = new Usuario(form.nomeDeUsuario(), senha);
		form.tiposPerfis().forEach(tipo -> usuario.adicionarPerfil(tipo));
		Usuario usuarioSalvo = repository.salvar(usuario);
		return usuarioSalvo;
	}
	
	public Usuario criarUsuarioDefaultParaPessoa(Pessoa pessoa, List<TipoPerfil> tiposPerfis) {
			String nomeDeUsuario = pessoa.getCPF();
			String senha = gerarSenhaParaPessoa(pessoa);
			CriaUsuarioForm form = new CriaUsuarioForm(nomeDeUsuario, senha, tiposPerfis);
			return criarUsuario(form);
	}
	
	private String gerarSenhaParaPessoa(Pessoa pessoa) {
		String primeiros4DigitosDoCPF = pessoa.getCPF().substring(0, 4);
		return "Pw" + primeiros4DigitosDoCPF + "@";
	}
}
