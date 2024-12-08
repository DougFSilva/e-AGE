package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzValidator {

	private ClazzRepository repository;

	public void validateUniqueCode(String code) {
		if (repository.existsByCode(code)) {
			throw new ClazzOperationException(String.format("Course with title %s already exists!", code));

		}
	}

	public void validateOpenCourse(Course course) {
		if (course.getIsClosed()) {
			throw new ClazzOperationException("It is not possible to create a class for a closed course!");
		}
	}
	
	

}
