package com.dougfsilva.e_AGE;

import com.dougfsilva.e_AGE.domain.utilities.Logger;

public class TestLogger implements Logger{

	@Override
	public void error(String mensagem) {
		System.out.println(mensagem);
		
	}

	@Override
	public void warn(String mensagem) {
		System.out.println(mensagem);
		
	}

	@Override
	public void info(String mensagem) {
		System.out.println(mensagem);
		
	}

	@Override
	public void debug(String mensagem) {
		System.out.println(mensagem);
		
	}

	@Override
	public void trace(String mensagem) {
		System.out.println(mensagem);
		
	}

}
