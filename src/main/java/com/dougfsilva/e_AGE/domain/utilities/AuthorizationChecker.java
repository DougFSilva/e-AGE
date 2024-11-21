package com.dougfsilva.e_AGE.domain.utilities;

import com.dougfsilva.e_AGE.domain.user.Profile;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.exception.UnauthorizedAccessException;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AuthorizationChecker {

	private final User user;
	
	private final Logger logger;
	
	private final Class<?> clazz;

	public void requireAdmin() {
		if (!user.getProfiles().contains(new Profile(ProfileType.ADMIN))) {
			logger.warn(String.format("Class %S - User %S without access authorization", clazz, user));
			throw new UnauthorizedAccessException("User without access authorization. ADMIN profile required!");
		}
	}

	public void requireProfessor() {
		if (!user.getProfiles().contains(new Profile(ProfileType.PROFESSOR))) {
			logger.warn(String.format("Class %S - User %S without access authorization", clazz, user));
			throw new UnauthorizedAccessException("User without access authorization. PROFESSOR profile required!");
		}
	}

	public void requireStudant() {
		if (!user.getProfiles().contains(new Profile(ProfileType.STUDENT))) {
			logger.warn(String.format("Class %S - User %S without access authorization", clazz, user));
			throw new UnauthorizedAccessException("User without access authorization. STUDENT profile required!");
		}
	}
}
