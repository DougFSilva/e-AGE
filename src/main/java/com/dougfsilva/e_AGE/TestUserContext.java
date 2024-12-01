package com.dougfsilva.e_AGE;

import java.util.Arrays;

import com.dougfsilva.e_AGE.domain.user.Password;
import com.dougfsilva.e_AGE.domain.user.Profile;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

public class TestUserContext implements UserContext {

	@Override
	public User getCurrentUser() {
		return new User("Douglas", new Password(), Arrays.asList(new Profile(ProfileType.STUDENT)));
	}

}
