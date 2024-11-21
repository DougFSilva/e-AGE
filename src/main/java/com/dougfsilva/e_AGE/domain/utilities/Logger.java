package com.dougfsilva.e_AGE.domain.utilities;

public interface Logger {

	void error(String mensagem);

	void warn(String mensagem);

	void info(String mensagem);

	void debug(String mensagem);

	void trace(String mensagem);
}
