package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseOpener {

	private final CourseRepository repository;
	private final StandardLogger logger;
	
	public CourseResponse openByID(String ID) {
		try {
			Course course = repository.findByIdOrThrow(ID);
			course.setClosingDate(null);
			course.setIsClosed(false);
			Course savedCourse = repository.save(course);
			logger.info(String.format("Opened Course ID %s,  %s", savedCourse.getID(), savedCourse.getTitle()));
			return CourseResponse.fromCourse(savedCourse);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while opening course ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new CourseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when opening course ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}
	
}
