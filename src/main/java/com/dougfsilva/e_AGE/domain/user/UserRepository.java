package com.dougfsilva.e_AGE.domain.user;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface UserRepository {

	User save(User user);
	void delete(User user);
	Optional<User> findByID(String ID);
	default User findByIdOrThrow(String ID) {
	    return findByID(ID)
	        .orElseThrow(() -> new ObjectNotFoundException("User not found for ID: " + ID));
	}
	Optional<User> findByUsername(String username);
	Page<User> findAllByProfyleType(ProfileType profileType, PageRequest pageRequest);
	Page<User> findAll(PageRequest pageRequest);
	Boolean existsByUsername(String username);
	
}