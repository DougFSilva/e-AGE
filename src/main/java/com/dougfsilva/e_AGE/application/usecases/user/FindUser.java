package com.dougfsilva.e_AGE.application.usecases.user;

import com.dougfsilva.e_AGE.application.dto.response.UserResponse;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.user.UserRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindUser {

	private final UserRepository repository;
	
	public UserResponse findByIDAsUSerResponse(String ID) {
		return UserResponse.fromUser(findByID(ID));
	}
	
	public User findByID(String ID) {
		return repository.findByID(ID).orElseThrow(() -> new ObjectNotFoundException(String.format("USer with ID %S not found!", ID)));
	}
}
