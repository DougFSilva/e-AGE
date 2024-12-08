package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.request.CreateClazzRequest;
import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.course.FindCourse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClazz {

	private final ClazzRepository repository;

	private final FindCourse findCourse;

	private final StandardLogger logger;

	public ClazzResponse execute(CreateClazzRequest request) {
		validateUniqueCode(request.getCode());
		Course course = findCourse.findByID(request.getCourseID());
		validateOpenCourse(course);
		Clazz clazz = new Clazz(request.getNumber(), request.getCode().toUpperCase(), course, false, request.getCreationDate());
		Clazz createdClazz = repository.save(clazz);
		logger.info(String.format("Create Class ID %s - %s ", createdClazz.getID(), createdClazz.getCode()));
		return ClazzResponse.fromClazz(createdClazz);
	}
	
	private void validateUniqueCode(String code) {
		if (repository.existsByCode(code)) {
			throw new DataIntegrityViolationException(String.format("Course with title %s already exists!", code));

		}
	}
	
	private void validateOpenCourse(Course course) {
		if (course.getIsClosed()) {
			throw new DataIntegrityViolationException("It is not possible to create a class for a closed course!");
		}
	}

}
