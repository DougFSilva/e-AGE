package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.request.UpdateClazzRequest;
import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.course.FindCourse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateClazz {

	private final ClazzRepository repository;

	private final FindClazz findClazz;

	private final FindCourse findCourse;

	private final ClazzValidator validator;

	private final StandardLogger logger;

	public ClazzResponse execute(UpdateClazzRequest request) {
		try {
			Clazz clazz = findClazz.findByID(request.getID());
			updateClazzData(clazz, request);
			Clazz updatedClazz = repository.save(clazz);
			logger.info(String.format("Update Class ID %s - %s", updatedClazz.getID(), updatedClazz.getCode()));
			return ClazzResponse.fromClazz(updatedClazz);
		} catch (Exception e) {
			logger.error("Unexpected error when updating class: " + e.getMessage());
			throw new ClazzOperationException("Error while update class", e);
		}

	}

	public void updateClazzData(Clazz clazz, UpdateClazzRequest request) {
		if (request.getCode() != null && !request.getCode().isBlank()
				&& !request.getCode().equalsIgnoreCase(clazz.getCode())) {
			validator.validateUniqueCode(request.getCode());
			clazz.setCode(request.getCode());
		}
		if (request.getCourseID() != null && !request.getCourseID().isBlank()) {
			Course course = findCourse.findByID(request.getCourseID());
			validator.validateOpenCourse(course);
			clazz.setCourse(course);
		}
		if (request.getNumber() != null) {
			clazz.setNumber(request.getNumber());
		}
	}

}
