package com.dougfsilva.e_AGE.dominio.pessoa.usuario;

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
	private List<Perfil> perfis;
	
	public Usuario(String nomeDeUsuario, SenhaDeUsuario senha, Boolean senhaAlterada, List<Perfil> perfis) {
		this.nomeDeUsuario = nomeDeUsuario;
		this.senha = senha;
		this.senhaAlterada = senhaAlterada;
		this.perfis = perfis;
	}
	
	public Boolean contemPerfil(TipoDePerfil tipo) {
		return this.getPerfis().stream().anyMatch(perfil -> perfil.getTipo() == tipo);
	}
}
