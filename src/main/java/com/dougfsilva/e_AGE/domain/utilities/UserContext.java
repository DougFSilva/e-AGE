package com.dougfsilva.e_AGE.domain.utilities;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.user.User;

public interface UserContext {

	Optional<User> getCurrentUser();
}
