package com.dougfsilva.e_AGE.domain.utilities;

import com.dougfsilva.e_AGE.application.dto.UserDto;
import com.dougfsilva.e_AGE.domain.user.Profile;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.exception.UnauthorizedAccessException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthChecker {
	
	private final UserContext userContext;
	
	private final Logger logger;

	public void requireAdmin(Class<?> clazz) {
		if (!getUser().getProfiles().contains(new Profile(ProfileType.ADMIN))) {
			logger.warn(String.format("%S - User %S without access authorization", clazz, getUserDto()));
			throw new UnauthorizedAccessException("User without access authorization. ADMIN profile required!");
		}
	}

	public void requireProfessor(Class<?> clazz) {
		if (!getUser().getProfiles().contains(new Profile(ProfileType.PROFESSOR))) {
			logger.warn(String.format("%S - User %S without access authorization", clazz, getUserDto()));
			throw new UnauthorizedAccessException("User without access authorization. PROFESSOR profile required!");
		}
	}

	public void requireStudent(Class<?> clazz) {
		if (!getUser().getProfiles().contains(new Profile(ProfileType.STUDENT))) {
			logger.warn(String.format("%S - User %S without access authorization", clazz, getUserDto()));
			throw new UnauthorizedAccessException("User without access authorization. STUDENT profile required!");
		}
	}
	
	private User getUser() {
		return this.userContext.getAuthenticatedUser();
	}
	
	private UserDto getUserDto() {
		return new UserDto(getUser());
	}
}
