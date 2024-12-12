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
			updateClazzData(clazz, request);
			Clazz updatedClazz = repository.save(clazz);
			logger.info(String.format("Updated class ID %s, code %s", updatedClazz.getID(), updatedClazz.getCode()));
			return ClazzResponse.fromClazz(updatedClazz);
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

	public void updateClazzData(Clazz clazz, UpdateClazzRequest request) {
		if (request.getCode() != null && !request.getCode().isBlank()
				&& !request.getCode().equalsIgnoreCase(clazz.getCode())) {
			validator.uniqueCode(request.getCode());
			clazz.setCode(request.getCode());
		}
		if (request.getCourseID() != null && !request.getCourseID().isBlank()) {
			Course course = courseRepository.findByIdOrThrow(request.getCourseID());
			validator.openCourse(course);
			clazz.setCourse(course);
		}
		if (request.getNumber() != null) {
			clazz.setNumber(request.getNumber());
		}
	}

}
