package com.dougfsilva.e_AGE.dominio.utilidades.log;

public interface Logger {

	void error(String mensagem);
	void error(String mensagem, Throwable e);
	void warn(String mensagem);
	void warn(String mensagem, Throwable e);
	void info(String mensagem);
	void info(String mensagem, Throwable e);
	void debug(String mensagem);
	void debug(String mensagem, Throwable e);
	void trace(String mensagem);
	void trace(String mensagem, Throwable e);
}
