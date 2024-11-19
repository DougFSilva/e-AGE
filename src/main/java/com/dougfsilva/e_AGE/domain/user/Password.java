package com.dougfsilva.e_AGE.domain.user;

import com.dougfsilva.e_AGE.domain.exception.InvalidUserOrPasswordException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Password {
	
	private String password;
	
	public Password(String password, PasswordEncoder encoder) {
		
		if(!password.matches("^(.*[0-9]).*$")){
			throw new InvalidUserOrPasswordException("A senha deve conter pelo menos um dígito!");
		}
		if(!password.matches("^(?=.*[A-Z]).*$")){
			throw new InvalidUserOrPasswordException("A senha deve conter pelo menos uma letra maiúscula!");
		}
		if(!password.matches("^(?=.*[a-z]).*$")){
			throw new InvalidUserOrPasswordException("A senha deve conter pelo menos uma letra minúscula!");
		}
		if(!password.matches("^(?=.*[@#$%^&-+=().]).*$")){
			throw new InvalidUserOrPasswordException("A senha deve conter pelo menos um caracter especial!");
		}
		if(!password.matches("^(?=\\S+$).*$")){
			throw new InvalidUserOrPasswordException("A senha náo deve conter espaços em branco!");
		}
		if(password.length()<6 || password.length()>20){
			throw new InvalidUserOrPasswordException("A senha deve conter de 6 a 20 caracteres!");
		}
		this.password = encoder.encode(password);
	}

}
