package com.dougfsilva.e_AGE.domain.person;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.pagination.PageRequest;

public interface PersonRepository {

	Person create(Person person);

	void delete(Person person);

	Person update(Person person);

	Optional<Person> findByID(String ID);

	Optional<Person> findByRg(String rg);

	List<Person> findAllByNameContains(String name, PageRequest pageRequest);

	List<Person> findAllByStateContains(String stateName, PageRequest pageRequest);

	List<Person> findAllByCityContains(String cityName, PageRequest pageRequest);

	List<Person> findAllByDistrictContains(String districtName, PageRequest pageRequest);

	List<Person> findAll(PageRequest pageRequest);

}
