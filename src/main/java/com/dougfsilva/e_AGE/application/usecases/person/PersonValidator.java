package com.dougfsilva.e_AGE.application.usecases.person;

import com.dougfsilva.e_AGE.domain.exception.PersonValidationException;
import com.dougfsilva.e_AGE.domain.person.PersonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonValidator {

	private final PersonRepository repository;
	
	public void uniqueRg(String rg) {
		if(repository.existsByRg(rg)) {
			throw new PersonValidationException(String.format("Person with rg %s already registered!", rg));
		}
	}
}
