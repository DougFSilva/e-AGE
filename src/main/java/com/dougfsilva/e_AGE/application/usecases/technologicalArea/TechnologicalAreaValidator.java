package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaOperationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TechnologicalAreaValidator {

	private final TechnologicalAreaRepository repository;
	private final CourseRepository courseRepository;

	public void uniqueTitle(String title) {
		if (repository.existsByTitle(title)) {
			throw new TechnologicalAreaOperationException(
					String.format("Technological Area with title %s already exists!", title));

		}
	}
	
	public void hasNoCoursesInTechnologicalArea(TechnologicalArea technologicalArea) {
		if (courseRepository.existsByTechnologialArea(technologicalArea)) {
			throw new TechnologicalAreaOperationException(
					String.format("The technological area %s cannot be deleted because there are courses still associated with it!", 
							technologicalArea.getTitle()));
		}
	}
}
