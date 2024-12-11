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
			logger.info(String.format("Created class ID %s, code %s ", createdClazz.getID(), createdClazz.getCode()));
			return ClazzResponse.fromClazz(createdClazz);
		} catch (ObjectNotFoundException | ClazzValidatorException e) {
			String message = String.format("Error while create class code %s : %s", request.getCode(), e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating class code %s : %s", request.getCode(), e.getMessage());
			logger.error(message, e);
			throw new ClazzOperationException(message, e);
		}

	}

}
