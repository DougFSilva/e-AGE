package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaValidationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TechnologicalAreaValidator {

	private final TechnologicalAreaRepository repository;

	public void uniqueTitle(String title) {
		if (repository.existsByTitle(title)) {
			throw new TechnologicalAreaValidationException(
					String.format("Technological Area with title %s already exists!", title));

		}
	}
	
}
