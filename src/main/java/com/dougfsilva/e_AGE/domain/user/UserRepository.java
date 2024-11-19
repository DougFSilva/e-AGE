package com.dougfsilva.e_AGE.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

	User create(User user);
	
	void delete(User user);
	
	User update(User user);
	
	Optional<User> findByUsername(String username);
	
	List<User> findAll();
}
