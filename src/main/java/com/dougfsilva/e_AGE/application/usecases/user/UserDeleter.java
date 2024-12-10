package com.dougfsilva.e_AGE.application.usecases.user;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.UserOperationException;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDeleter {

	private final UserRepository repository;
	private final UserFinder userFinder;
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			User user = userFinder.findByID(ID);
			repository.delete(user);
			logger.info(String.format("Deleted User ID %s - %s", user.getID(), user.getUsername()));
		} catch (Exception e) {
			logger.error("Unexpected error when deleting user: " + e.getMessage());
			throw new UserOperationException("Error while delete user", e);
		}
		
	}
}
