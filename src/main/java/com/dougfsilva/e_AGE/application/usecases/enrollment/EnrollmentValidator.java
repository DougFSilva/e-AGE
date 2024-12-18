package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentValidationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnrollmentValidator {

	private final EnrollmentRepository repository;
	
	public void uniqueRegistration(String registration) {
		if(repository.existsByRegistration(registration)) {
			throw new EnrollmentValidationException(String.format("Enrollment with registration %s already exists!", registration));
		}
	}
	
}
