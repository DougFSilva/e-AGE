package com.dougfsilva.e_AGE.domain.person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

	Person create(Person person);
	
	void delete(Person person);
	
	Person update(Person person);
	
	Optional<Person> findByRg(String rg);
	
	List<Person> findByNameContains(String name);
	
	List<Person> findAll();
	
}
