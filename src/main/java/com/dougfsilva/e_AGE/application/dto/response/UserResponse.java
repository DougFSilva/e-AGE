package com.dougfsilva.e_AGE.application.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UserResponse {

	private String ID;

	private String username;

	private List<String> profiles;

	public UserResponse(User user) {
		this.ID = user.getID();
		this.username = user.getUsername();
		this.profiles = user.getProfiles().stream().map(p -> p.getProfileType().name()).toList();
	}
	
	public static Page<UserResponse> fromPage(Page<User> users) {
		return new Page<UserResponse>(
				users.getContent()
				.stream()
				.map(UserResponse::new)
				.collect(Collectors.toList()), 
				users.getNumber(), 
				users.getSize(),
				users.getTotalElements(),
				users.getTotalPages(), 
				users.getHasContent(), 
				users.getIsFirst(),
				users.getIsLast());
	}
	
	public static UserResponse fromUser(User user) {
		return new UserResponse(user);
	}
	
}
