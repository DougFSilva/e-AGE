package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseValidatorException;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnterpriseValidator {

	private final EnterpriseRepository repository;
	private final StudentRepository studentRepository;
	
	public void uniqueTIN(String TIN) {
		if(repository.existsByTIN(TIN)) {
			throw new EnterpriseValidatorException(String.format("Enterprise with TIN %s already registered!", TIN));
		}
	}
	
	public void uniqueName(String name) {
		if(repository.existsByName(name)) {
			throw new EnterpriseValidatorException(String.format("Enterprise with name %s already exists!", name));
		}
	}
	
	public void hasNoStudentsRegisteredInTheEnterprise(Enterprise enterprise) {
		if(studentRepository.existsByEnterprise(enterprise)) {
			throw new EnterpriseValidatorException(
					String.format("The Enterprise %s cannot be deleted because there are students still associated with it!", 
							enterprise.getName()));
		}
	}
}
