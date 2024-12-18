package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzValidationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzValidator {

	private final ClazzRepository repository;

	public void uniqueCode(String code) {
		if (repository.existsByCode(code)) {
			throw new ClazzValidationException(String.format("Course with title %s already exists!", code));
		}
	}

}
