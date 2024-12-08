package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeValidator {

	private final EmployeeRepository employeeRepository;
	
	public void validateUniqueRegistration(String registration) {
		if (employeeRepository.existsByRegistration(registration)) {
			throw new EmployeeOperationException(String.format("Employee with registration %s already registered!", registration));
		}
	}
}
