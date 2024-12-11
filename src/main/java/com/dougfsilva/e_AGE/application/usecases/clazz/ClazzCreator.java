package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.request.CreateClazzRequest;
import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.course.CourseFinder;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.ClazzValidatorException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzCreator {

	private final ClazzRepository repository;
	private final CourseFinder courseFinder;
	private final ClazzValidator validator;
	private final StandardLogger logger;

	public ClazzResponse create(CreateClazzRequest request) {
		try {
			validator.uniqueCode(request.getCode());
			Course course = courseFinder.findByID(request.getCourseID());
			validator.openCourse(course);
			Clazz clazz = new Clazz(request.getNumber(), request.getCode().toUpperCase(), course, false,
					request.getCreationDate());
			Clazz createdClazz = repository.save(clazz);
			logger.info(String.format("Create Class ID %s - %s ", createdClazz.getID(), createdClazz.getCode()));
			return ClazzResponse.fromClazz(createdClazz);
		}catch (ObjectNotFoundException e) {
			String message = String.format("Error while create Class %s : %s", request.getCode(), e.getMessage());
			logger.warn(message);
			throw new ClazzOperationException(message);
		}catch (ClazzValidatorException e) {
			logger.error("Error while create class: " + e.getMessage());
			throw new ClazzOperationException("Error while create Class: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Unexpected error when creating Class: " + e.getMessage());
			throw new ClazzOperationException("Unexpected error when creating Class", e);
		}

	}

}
