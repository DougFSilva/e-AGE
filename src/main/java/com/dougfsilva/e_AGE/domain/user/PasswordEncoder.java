package com.dougfsilva.e_AGE.domain.user;

public interface PasswordEncoder {

	String encode(String password);
	Boolean compare(String password, String encodedPassword);
}
