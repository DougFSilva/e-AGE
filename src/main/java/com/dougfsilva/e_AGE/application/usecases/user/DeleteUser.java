package com.dougfsilva.e_AGE.application.usecases.user;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteUser {

	private final UserRepository repository;
	
	private final FindUser findUser;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		User user = findUser.findByID(ID);
		repository.delete(user);
		logger.info(String.format("Deleted User ID %S - %S", user.getID(), user.getUsername()));
	}
}
