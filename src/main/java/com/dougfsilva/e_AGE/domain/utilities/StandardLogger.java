package com.dougfsilva.e_AGE.domain.utilities;

import com.dougfsilva.e_AGE.application.dto.UserDto;

public class StandardLogger {

	public static void createdObjectLogger(Object object, AuthChecker checker, Logger logger) {
		logger.info(String.format("%S created by %S", object, new UserDto(checker.getUser())));
	}

	public static void deletedObjectLogger(Object object, AuthChecker checker, Logger logger) {
		logger.info(String.format("%S deleted by %S", object, new UserDto(checker.getUser())));
	}

	public static void updatedObjectLogger(Object object, AuthChecker checker, Logger logger) {
		logger.info(String.format("%S updated by %S", object, new UserDto(checker.getUser())));
	}

}
