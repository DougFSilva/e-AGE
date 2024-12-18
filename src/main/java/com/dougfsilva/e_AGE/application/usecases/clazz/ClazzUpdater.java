package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.request.UpdateClazzRequest;
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
public class ClazzUpdater {

	private final ClazzRepository repository;
	private final CourseRepository courseRepository;
	private final ClazzValidator validator;
	private final StandardLogger logger;

	public ClazzResponse update(UpdateClazzRequest request) {
		try {
			Clazz clazz = repository.findByIdOrThrow(request.getID());
			Clazz updatedClazz = updateClazzData(clazz, request);
			Clazz savedClazz = repository.save(updatedClazz);
			logger.info(String.format("Updated class ID %s, code %s", savedClazz.getID(), savedClazz.getCode()));
			return ClazzResponse.fromClazz(savedClazz);
		} catch (ObjectNotFoundException | ClazzValidationException e) {
			String message = String.format("Error while updating class code %s : %s", request.getCode(), e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when updating class code %s : %s", request.getCode(), e.getMessage());
			logger.error(message, e);
			throw new ClazzOperationException(message, e);
		}
	}

	private Clazz updateClazzData(Clazz clazz, UpdateClazzRequest request) {
		if (request.getCode() != null && !request.getCode().isBlank() && !request.getCode().equalsIgnoreCase(clazz.getCode())) {
			validator.uniqueCode(request.getCode());
			clazz.setCode(request.getCode());
		}
		if (request.getCourseID() != null && !request.getCourseID().isBlank()) {
			Course course = courseRepository.findByIdOrThrow(request.getCourseID());
			ensureIsOpenCourse(course);
			clazz.setCourse(course);
		}
		if (request.getNumber() != null) {
			clazz.setNumber(request.getNumber());
		}
		return clazz;
	}
	
	private void ensureIsOpenCourse(Course course) {
		if (course.getIsClosed()) {
			throw new ClazzValidationException("It is not possible to updated a class for a closed course!");
		}
	}

}
