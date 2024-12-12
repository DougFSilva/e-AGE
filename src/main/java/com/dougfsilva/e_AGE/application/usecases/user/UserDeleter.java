package com.dougfsilva.e_AGE.application.usecases.user;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.UserOperationException;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDeleter {

	private final UserRepository repository;
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			User user = repository.findByIdOrThrow(ID);
			repository.delete(user);
			logger.info(String.format("Deleted User ID %s - %s", user.getID(), user.getUsername()));
		}  catch (ObjectNotFoundException e) {
			String message = String.format("Error while deleting user ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new UserOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting user ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new UserOperationException(message, e);
		}
	}
}
