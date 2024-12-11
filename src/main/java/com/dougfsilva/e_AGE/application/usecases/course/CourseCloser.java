package com.dougfsilva.e_AGE.application.usecases.course;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.CourseValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseCloser {

	private final CourseRepository repository;
	private final ClazzRepository clazzRepository;
	private final CourseFinder courseFinder;
	private StandardLogger logger;

	public CourseResponse closerByID(String ID, LocalDate date) {
		try {
			Course course = courseFinder.findByID(ID);
			List<Clazz> clazzes = clazzRepository.findAllByCourse(course);
			checkForOpenedClazz(clazzes);
			Course closedCourse = closeCourse(course, date);
			logger.info(String.format("Closed Course ID %s, %s", closedCourse.getID(), closedCourse.getTitle()));
			return CourseResponse.fromCourse(closedCourse);
		} catch (ObjectNotFoundException | CourseValidationException e) {
			String message = String.format("Error while close course ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when closing course ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new ClazzOperationException(message, e);
		}
	}

	private void checkForOpenedClazz(List<Clazz> clazzes) {
		if (clazzes.stream().anyMatch(clazz -> !clazz.getIsClosed())) {
			throw new CourseValidationException(
					"It is not possible to close the course, as there are still open classes belonging to it!");
		}
	}

	private Course closeCourse(Course course, LocalDate date) {
		course.setIsClosed(true);
		course.setClosingDate(date);
		return repository.save(course);
	}
}
