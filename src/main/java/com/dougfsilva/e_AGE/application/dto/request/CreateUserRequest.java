package com.dougfsilva.e_AGE.application.dto.request;

import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.user.Profile;
import com.dougfsilva.e_AGE.domain.user.ProfileType;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateUserRequest {

	private String username;

	private String password;

	private List<Profile> profiles;

	public CreateUserRequest(String username, String password, List<ProfileType> profiles) {
		if (username == null || username.isBlank()) {
			throw new IllegalArgumentException("Username cannot be null or empty!");
		}
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException("Password cannot be null or empty!");
		}
		if (profiles == null || profiles.isEmpty()) {
			throw new IllegalArgumentException("Profiles list cannot be null or empty!");
		}

		this.username = username;
		this.password = password;
		this.profiles = profiles.stream().map(Profile::new).collect(Collectors.toList());
	}

}
