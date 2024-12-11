package com.dougfsilva.e_AGE.application.usecases.utilities;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.Logger;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

public class StandardLogger {

	private final Optional<User> user;

	private final Logger logger;

	public StandardLogger(UserContext userContext, Logger logger) {
		this.user = userContext.getCurrentUser();
		this.logger = logger;
	}

	public void error(String message) {
		logger.error(messageBuilder(message));
	}
	
	public void error(String message, Throwable e) {
		logger.error(messageBuilder(message));
	}
	
	public void warn(String message) {
		logger.warn(messageBuilder(message));
	}
	
	public void warn(String message, Throwable e) {
		logger.warn(messageBuilder(message));
	}
	
	public void info(String message) {
		logger.info(messageBuilder(message));
	}
	
	public void info(String message, Throwable e) {
		logger.info(messageBuilder(message));
	}
	
	public void debug(String message) {
		logger.debug(messageBuilder(message));
	}
	
	public void debug(String message, Throwable e) {
		logger.debug(messageBuilder(message));
	}
	
	public void trace(String message) {
		logger.trace(messageBuilder(message));
	}
	
	public void trace(String message, Throwable e) {
		logger.trace(messageBuilder(message));
	}
	
	private String messageBuilder(String message) {
		if(user.isPresent()) {
			message = String.format(message + " by %s - %s ", user.get().getID(), user.get().getUsername());
		}
		return message;
	}

}
