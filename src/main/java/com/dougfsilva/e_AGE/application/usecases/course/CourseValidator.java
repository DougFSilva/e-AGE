package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseValidationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseValidator {

	private final CourseRepository repository;

	public void uniqueTitle(String title) {
		if(repository.existsByTitle(title)) {
			throw new CourseValidationException(String.format("Course with title %s already exists!", title));
		}
	}
}
