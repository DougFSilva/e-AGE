package com.dougfsilva.e_AGE.dominio.utilidades.log;

import java.util.Optional;

import com.dougfsilva.e_AGE.dominio.pessoa.usuario.Usuario;
import com.dougfsilva.e_AGE.dominio.utilidades.UsuarioAutenticado;

public class LogPadrao {

	private final Optional<Usuario> usuario;

	private final Logger logger;

	public LogPadrao(UsuarioAutenticado usuarioAutenticado, Logger logger) {
		this.usuario = usuarioAutenticado.buscarUsuarioAtual();
		this.logger = logger;
	}

	public void error(String mensagem) {
		logger.error(construirMensagem(mensagem));
	}
	
	public void error(String mensagem, Throwable e) {
		logger.error(construirMensagem(mensagem));
	}
	
	public void warn(String mensagem) {
		logger.warn(construirMensagem(mensagem));
	}
	
	public void warn(String mensagem, Throwable e) {
		logger.warn(construirMensagem(mensagem));
	}
	
	public void info(String mensagem) {
		logger.info(construirMensagem(mensagem));
	}
	
	public void info(String mensagem, Throwable e) {
		logger.info(construirMensagem(mensagem));
	}
	
	public void debug(String mensagem) {
		logger.debug(construirMensagem(mensagem));
	}
	
	public void debug(String mensagem, Throwable e) {
		logger.debug(construirMensagem(mensagem));
	}
	
	public void trace(String mensagem) {
		logger.trace(construirMensagem(mensagem));
	}
	
	public void trace(String mensagem, Throwable e) {
		logger.trace(construirMensagem(mensagem));
	}
	
	private String construirMensagem(String mensagem) {
		if(usuario.isPresent()) {
			mensagem = String.format(mensagem + " por %s - %s ", usuario.get().getID(), usuario.get().getNomeDeUsuario());
		}
		return mensagem;
	}
}
