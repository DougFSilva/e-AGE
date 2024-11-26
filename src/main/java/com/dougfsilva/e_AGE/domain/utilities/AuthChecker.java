package com.dougfsilva.e_AGE.domain.utilities;

import java.util.Arrays;
import java.util.List;

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

	public void requireProfiles(Class<?> clazzContext, List<ProfileType> profileTypes) {
		List<Profile> profiles = profileTypes.stream().map(profileType -> new Profile(profileType)).toList();
		Boolean hasProfile = getUser().getProfiles().stream().anyMatch(profiles::contains);
		List<String> profilesNames = profiles.stream().map(p -> p.getProfileType().name()).toList();
		if (!hasProfile) {
			logger.warn(
					String.format("%S - User %S without access authorization", clazzContext, new UserDto(getUser())));
			throw new UnauthorizedAccessException(
					String.format("User without access authorization!,  %S profiles required! ", profilesNames));
		}
	}

	public void requireAnyProfiles(Class<?> clazzContext) {
		List<Profile> profiles = Arrays.asList(new Profile(ProfileType.ADMIN), new Profile(ProfileType.PROFESSOR),
				new Profile(ProfileType.STUDENT), new Profile(ProfileType.VIEWER));
		Boolean hasProfile = getUser().getProfiles().stream().anyMatch(profiles::contains);
		List<String> profilesNames = profiles.stream().map(p -> p.getProfileType().name()).toList();
		if (!hasProfile) {
			logger.warn(
					String.format("%S - User %S without access authorization", clazzContext, new UserDto(getUser())));
			throw new UnauthorizedAccessException(
					String.format("User without access authorization!,  %S profiles required! ", profilesNames));
		}
	}

	public User getUser() {
		return this.userContext.getAuthenticatedUser();
	}

}
