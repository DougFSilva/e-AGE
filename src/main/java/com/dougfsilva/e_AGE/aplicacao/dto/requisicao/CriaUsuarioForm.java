package com.dougfsilva.e_AGE.aplicacao.dto.requisicao;

import com.dougfsilva.e_AGE.dominio.exception.ErroDeValidacaoDeCamposException;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;

public record CriaUsuarioForm(String nomeDeUsuario, String senha, TipoPerfil tipoPerfil) {
	
	public CriaUsuarioForm(String nomeDeUsuario, String senha, TipoPerfil tipoPerfil) {
        if (nomeDeUsuario == null || nomeDeUsuario.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo nome de usuário não pode ser nulo ou vazio");
        }
        if (senha == null || senha.isBlank()) {
            throw new ErroDeValidacaoDeCamposException("O campo senha não pode ser nulo ou vazio");
        }
        if (tipoPerfil == null) {
            throw new ErroDeValidacaoDeCamposException("O campo tipo de perfil não pode ser nulo");
        }
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.tipoPerfil = tipoPerfil;
    }
}
