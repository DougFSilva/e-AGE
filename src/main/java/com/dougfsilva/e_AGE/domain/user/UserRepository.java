package com.dougfsilva.e_AGE.domain.user;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.pagination.PageRequest;

public interface UserRepository {

	User create(User user);
	
	void delete(User user);
	
	User update(User user);
	
	Optional<User> findByID(String ID);
	
	Optional<User> findByUsername(String username);
	
	List<User> findAllByProfyleType(ProfileType profileType, PageRequest pageRequest);
	
	List<User> findAll(PageRequest pageRequest);
}
