package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.request.UpdateClazzRequest;
import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.course.FindCourse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateClazz {

	private final ClazzRepository repository;
	
	private final FindClazz findClazz;
	
	private final FindCourse findCourse;
	
	private final StandardLogger logger;
	
	public ClazzResponse execute(UpdateClazzRequest request) {
		Clazz clazz = findClazz.findByID(request.getID());
		if(!request.getCode().equalsIgnoreCase(clazz.getCode()) && request.getCode() != null && !request.getCode().isBlank()) {
			repository.findByCode(request.getCode().toUpperCase()).ifPresent(c -> {
				throw new DataIntegrityViolationException(String.format("Course with title %S already exists!", request.getCode()));
			});
			clazz.setCode(request.getCode());
		}
		if(request.getCourseID() != null && !request.getCourseID().isBlank()) {
			Course course = findCourse.findByID(request.getCourseID());
			if(course.getIsClosed()) {
				throw new DataIntegrityViolationException("It is not possible to create a class for a closed course!");
			}
			clazz.setCourse(course);
		}
		if(request.getNumber() != null) {
			clazz.setNumber(request.getNumber());
		}
		Clazz updatedClazz = repository.save(clazz);
		logger.updatedObjectLog(updatedClazz);
		return ClazzResponse.fromClazz(updatedClazz);
	}
	
}
