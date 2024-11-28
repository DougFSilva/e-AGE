package com.dougfsilva.e_AGE.domain.user;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface UserRepository {

	User save(User user);
	
	void delete(User user);
	
	Optional<User> findByID(String ID);
	
	Optional<User> findByUsername(String username);
	
	Page<User> findAllByProfyleType(ProfileType profileType, PageRequest pageRequest);
	
	Page<User> findAll(PageRequest pageRequest);
}
