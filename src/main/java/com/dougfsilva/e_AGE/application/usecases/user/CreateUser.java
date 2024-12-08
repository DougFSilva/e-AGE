package com.dougfsilva.e_AGE.application.usecases.user;

import com.dougfsilva.e_AGE.application.dto.request.CreateUserRequest;
import com.dougfsilva.e_AGE.application.dto.response.UserResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;
import com.dougfsilva.e_AGE.domain.user.Password;
import com.dougfsilva.e_AGE.domain.user.PasswordEncoder;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUser {

	private final UserRepository repository;
	
	private final PasswordEncoder encoder;
	
	private final StandardLogger logger;
	
	public UserResponse execute(CreateUserRequest request) {
		if(repository.existsByUsername(request.getUsername())) {
			throw new DataIntegrityViolationException(String.format("Username %S already exists!", request.getUsername()));
		}
		User user = new User(request.getUsername(), new Password(request.getPassword(), encoder), request.getProfiles(), false);
		User createdUser = repository.save(user);
		logger.info(String.format("Created User ID %S - %S", createdUser.getID(), createdUser.getUsername()));
		return UserResponse.fromUser(createdUser);
	}
}
