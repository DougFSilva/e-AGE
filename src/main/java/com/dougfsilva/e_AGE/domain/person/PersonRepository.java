package com.dougfsilva.e_AGE.domain.person;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface PersonRepository {

	Person save(Person person);

	void delete(Person person);

	Optional<Person> findByID(String ID);

	Optional<Person> findByRg(String rg);
	
	Optional<Person> findByUser(User user);

	Page<Person> findAllByNameContains(String name, PageRequest pageRequest);

	Page<Person> findAllByStateContains(String stateName, PageRequest pageRequest);

	Page<Person> findAllByCityContains(String cityName, PageRequest pageRequest);

	Page<Person> findAllByDistrictContains(String districtName, PageRequest pageRequest);

	Page<Person> findAll(PageRequest pageRequest);

}
