package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseOperationException;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnterpriseValidator {

	private final EnterpriseRepository repository;
	
	private final StudentRepository studentRepository;
	
	public void validateUniqueTIN(String TIN) {
		if(repository.existsByTIN(TIN)) {
			throw new EnterpriseOperationException(String.format("Enterprise with TIN %s already registered!", TIN));
		}
	}
	
	public void validateUniqueName(String name) {
		if(repository.existsByName(name)) {
			throw new EnterpriseOperationException(String.format("Enterprise with name %s already exists!", name));
		}
	}
	
	public void validateNoStudentsRegisteredWithTheEnterprise(Enterprise enterprise) {
		if(studentRepository.existsByEnterprise(enterprise)) {
			throw new EnterpriseOperationException(
					String.format("The Enterprise %s cannot be deleted because there are Students still associated with it!", 
							enterprise.getName()));
		}
	}
}
