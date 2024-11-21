package com.dougfsilva.e_AGE.domain.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"username"})
@ToString
public class User {
	
	private String ID;
	
	private String username;
	
	private Password password;
	
	private List<Profile> profiles;

	public User(String username, Password password, List<Profile> profiles) {
		this.username = username;
		this.password = password;
		this.profiles = profiles;
	}

	
}
