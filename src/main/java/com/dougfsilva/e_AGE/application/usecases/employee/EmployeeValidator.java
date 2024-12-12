package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeValidationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeValidator {

	private final EmployeeRepository employeeRepository;
	
	public void uniqueRegistration(String registration) {
		if (employeeRepository.existsByRegistration(registration)) {
			throw new EmployeeValidationException(String.format("Employee with registration %s already registered!", registration));
		}
	}
}
