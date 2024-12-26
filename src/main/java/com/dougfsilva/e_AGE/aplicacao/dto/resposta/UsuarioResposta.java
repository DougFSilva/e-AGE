package com.dougfsilva.e_AGE.aplicacao.dto.resposta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.dominio.pessoa.usuario.TipoPerfil;
import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.utilidades.paginacao.Pagina;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"nomeDeUsuario"})
public class UsuarioResposta {

	private String ID;
	private String nomeDeUsuario;
	private Boolean senhaAlterada;
	private List<TipoPerfil> perfis = new ArrayList<TipoPerfil>();
	
	public UsuarioResposta(Usuario usuario) {
		this.ID = usuario.getID();
		this.nomeDeUsuario = usuario.getNomeDeUsuario();
		this.senhaAlterada = usuario.getSenhaAlterada();
		this.perfis = usuario.getPerfis().stream().map(perfil -> perfil.getTipo()).collect(Collectors.toList());
	}
	
	public static Pagina<UsuarioResposta> dePagina(Pagina<Usuario> usuarios) {
		return new Pagina<UsuarioResposta>(
				usuarios.getConteudo()
				.stream()
				.map(UsuarioResposta::new)
				.collect(Collectors.toList()), 
				usuarios.getNumero(), 
				usuarios.getTamanho(),
				usuarios.getTotalDeElementos(),
				usuarios.getTotalDePaginas(), 
				usuarios.getTemConteudo(), 
				usuarios.getPrimeira(),
				usuarios.getUltima());
	}
	
	public static UsuarioResposta deUsuario(Usuario usuario) {
        return new UsuarioResposta(usuario);
	}
}
