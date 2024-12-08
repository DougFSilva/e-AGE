package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseValidator {

	private CourseRepository repository;

	public void validateUniqueTitle(String title) {
		if(repository.existsByTitle(title)) {
			throw new CourseOperationException(String.format("Course with title %s already exists!", title));
		}
	}
}
