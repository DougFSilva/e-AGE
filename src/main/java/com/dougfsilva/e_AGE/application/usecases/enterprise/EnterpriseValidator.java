package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseValidationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnterpriseValidator {

	private final EnterpriseRepository repository;
	
	public void uniqueTIN(String TIN) {
		if(repository.existsByTIN(TIN)) {
			throw new EnterpriseValidationException(String.format("Enterprise with TIN %s already registered!", TIN));
		}
	}
	
	public void uniqueName(String name) {
		if(repository.existsByName(name)) {
			throw new EnterpriseValidationException(String.format("Enterprise with name %s already exists!", name));
		}
	}
	
}
