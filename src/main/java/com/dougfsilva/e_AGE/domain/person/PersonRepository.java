package com.dougfsilva.e_AGE.domain.person;

import java.util.Optional;

public interface PersonRepository {

	Optional<Person> findByRg(String rg);
	
	Boolean existsByRg(String rg);
	
}
