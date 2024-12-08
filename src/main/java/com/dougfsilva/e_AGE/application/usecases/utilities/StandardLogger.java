package com.dougfsilva.e_AGE.application.usecases.utilities;

import com.dougfsilva.e_AGE.application.dto.response.UserResponse;
import com.dougfsilva.e_AGE.domain.utilities.Logger;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

public class StandardLogger {

	private final UserResponse user;

	private final Logger logger;

	public StandardLogger(UserContext userContext, Logger logger) {
		this.user = UserResponse.fromUser(userContext.getCurrentUser());
		this.logger = logger;
	}

	public void error(String message) {
		logger.info(String.format(message + " by %S - ", user));
	}
	
	public void warn(String message) {
		logger.info(String.format(message + " by %S - ", user));
	}
	
	public void info(String message) {
		logger.info(String.format(message + " by %S - ", user));
	}
	
	public void debug(String message) {
		logger.info(String.format(message + " by %S - ", user));
	}
	
	public void trace(String message) {
		logger.info(String.format(message + " by %S - ", user));
	}

}
