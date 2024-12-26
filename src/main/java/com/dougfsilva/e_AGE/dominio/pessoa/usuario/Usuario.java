package com.dougfsilva.e_AGE.dominio.pessoa.usuario;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"nomeDeUsuario"})
public class Usuario {

	private String ID;
	private String nomeDeUsuario;
	private SenhaDeUsuario senha;
	private Boolean senhaAlterada;
	private List<Perfil> perfis = new ArrayList<Perfil>();
	
	public Usuario(String nomeDeUsuario, SenhaDeUsuario senha) {
		this.nomeDeUsuario = nomeDeUsuario;
		this.senha = senha;
		this.senhaAlterada = false;
	}
	
	public Boolean contemPerfil(TipoPerfil tipo) {
		return this.getPerfis().stream().anyMatch(perfil -> perfil.getTipo() == tipo);
	}
	
	public void adicionarPerfil(TipoPerfil tipoPerfil) {
		this.perfis.add(new Perfil(tipoPerfil));
	}
	
	public void removerPerfil(TipoPerfil tipoPerfil) {
		this.perfis.removeIf(perfil -> perfil.getTipo().equals(tipoPerfil));
	}
}
