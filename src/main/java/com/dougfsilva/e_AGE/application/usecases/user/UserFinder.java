package com.dougfsilva.e_AGE.application.usecases.user;

import com.dougfsilva.e_AGE.application.dto.response.UserResponse;
import com.dougfsilva.e_AGE.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFinder {

	private final UserRepository repository;
	
	public UserResponse findByID(String ID) {
		return UserResponse.fromUser(repository.findByIdOrThrow(ID));
	}
	
}
