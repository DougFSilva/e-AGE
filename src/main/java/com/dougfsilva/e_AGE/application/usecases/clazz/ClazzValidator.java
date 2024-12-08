package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.dropout.DropoutRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzValidator {

	private final ClazzRepository repository;
	
	private final EnrollmentRepository enrollmentRepository;
	
	private final DropoutRepository dropoutRepository; 

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
	
	public void validateNoEnrollmentRegisteredInTheClazz(Clazz clazz) {
		if (enrollmentRepository.existsByClazz(clazz) || dropoutRepository.existsByClazz(clazz)) {
			throw new ClazzOperationException(String.format(
					"The Class %s cannot be deleted because there are Students still associated with it!",
					clazz.getCode()));
		}
	}
	
	

}
