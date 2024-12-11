package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseOpener {

	private final CourseRepository repository;
	private final CourseFinder courseFinder;
	private final StandardLogger logger;
	
	public CourseResponse openByID(String ID) {
		try {
			Course course = courseFinder.findByID(ID);
			Course openedCourse = openCourse(course);
			logger.info(String.format("Opened Course ID %s,  %s", openedCourse.getID(), openedCourse.getTitle()));
			return CourseResponse.fromCourse(openedCourse);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while open course ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when opening course ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}
	
	private Course openCourse(Course course) {
		course.setClosingDate(null);
		course.setIsClosed(false);
		return repository.save(course);
	}
}
