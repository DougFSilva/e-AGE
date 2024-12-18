package com.dougfsilva.e_AGE.application.usecases.course;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseCloser {

	private final CourseRepository repository;
	private final ClazzRepository clazzRepository;
	private StandardLogger logger;

	public CourseResponse closerByID(String ID) {
		try {
			Course course = repository.findByIdOrThrow(ID);
			List<Clazz> clazzes = clazzRepository.findAllByCourse(course);
			ensureIsNoOpenedClazz(clazzes);
			course.setIsClosed(true);
			course.setClosingDate(LocalDate.now());
			Course savedCourse = repository.save(course);
			logger.info(String.format("Closed Course ID %s, %s", savedCourse.getID(), savedCourse.getTitle()));
			return CourseResponse.fromCourse(savedCourse);
		} catch (ObjectNotFoundException | OperationNotAllowedException e) {
			String message = String.format("Error while closing course ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new CourseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when closing course ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}

	private void ensureIsNoOpenedClazz(List<Clazz> clazzes) {
		if (clazzes.stream().anyMatch(clazz -> !clazz.getIsClosed())) {
			throw new OperationNotAllowedException(
					"It is not possible to close the course, as there are still open classes belonging to it!");
		}
	}
}
