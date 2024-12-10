package com.dougfsilva.e_AGE.application.dto.request;

import java.util.List;

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

	public CreateUserRequest(String username, String password, ProfileType profile) {
		if (username == null || username.isBlank()) {
			throw new IllegalArgumentException("Username cannot be null or empty!");
		}
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException("Password cannot be null or empty!");
		}
		if (profile == null || profiles.isEmpty()) {
			throw new IllegalArgumentException("Profiles list cannot be null or empty!");
		}

		this.username = username;
		this.password = password;
		this.profiles.add(new Profile(profile));
	}

}
