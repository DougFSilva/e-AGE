package com.dougfsilva.e_AGE.application.usecases.clazz;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.application.dto.request.CreateClazzRequest;
import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.ClazzValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzCreator {

	private final ClazzRepository repository;
	private final CourseRepository courseRepository;
	private final ClazzValidator validator;
	private final StandardLogger logger;

	public ClazzResponse create(CreateClazzRequest request) {
		try {
			validator.uniqueCode(request.getCode());
			Course course = courseRepository.findByIdOrThrow(request.getCourseID());
			ensureIsOpenCourse(course);
			Clazz clazz = new Clazz(request.getNumber(), request.getCode().toUpperCase(), course, false, LocalDate.now());
			Clazz savedClazz = repository.save(clazz);
			logger.info(String.format("Created class ID %s, code %s ", savedClazz.getID(), savedClazz.getCode()));
			return ClazzResponse.fromClazz(savedClazz);
		} catch (ObjectNotFoundException | ClazzValidationException e) {
			String message = String.format("Error while creating class code %s : %s", request.getCode(), e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating class code %s : %s", request.getCode(), e.getMessage());
			logger.error(message, e);
			throw new ClazzOperationException(message, e);
		}
	}
	
	private void ensureIsOpenCourse(Course course) {
		if (course.getIsClosed()) {
			throw new ClazzValidationException("It is not possible to create a class for a closed course!");
		}
	}

}
