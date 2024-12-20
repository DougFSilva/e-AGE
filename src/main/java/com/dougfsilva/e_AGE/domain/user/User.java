package com.dougfsilva.e_AGE.domain.user;

import java.util.ArrayList;
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
	private Boolean passwordChanged;
	private List<Profile> profiles = new ArrayList<>();

	public User(String username, Password password, List<Profile> profiles, Boolean passwordChanged) {
		this.username = username;
		this.password = password;
		this.profiles = profiles;
		this.passwordChanged = passwordChanged;
	}
	
	public Boolean checkContainsProfile(ProfileType profileType) {
		return this.getProfiles().stream().anyMatch(profile -> profile.getProfileType() == profileType);
	}
	
}
