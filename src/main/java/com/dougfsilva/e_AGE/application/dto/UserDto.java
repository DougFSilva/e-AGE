package com.dougfsilva.e_AGE.application.dto;

import java.util.List;

import com.dougfsilva.e_AGE.domain.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UserDto {

	private String ID;

	private String username;

	private List<String> profiles;

	public UserDto(User user) {
		this.ID = user.getID();
		this.username = user.getUsername();
		this.profiles = user.getProfiles().stream().map(p -> p.getProfileType().name()).toList();
	}
	
}
