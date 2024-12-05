package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.request.CreateClazzRequest;
import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.course.FindCourse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClazz {

	private final ClazzRepository repository;

	private final FindCourse findCourse;

	private final StandardLogger logger;

	public ClazzResponse execute(CreateClazzRequest request) {
		repository.findByCode(request.getCode().toUpperCase()).ifPresent(c -> {
			throw new DataIntegrityViolationException(String.format(" Clazz with code %S already exists!", c.getCode()));
		});
		Course course = findCourse.findByID(request.getCourseID());
		if(course.getIsClosed()) {
			throw new DataIntegrityViolationException("It is not possible to create a class for a closed course!");
		}
		Clazz clazz = new Clazz(request.getNumber(), request.getCode().toUpperCase(), course, false, request.getCreationDate());
		Clazz createdClazz = repository.save(clazz);
		logger.createdObjectLog(createdClazz);
		return ClazzResponse.fromClazz(createdClazz);
	}

}
