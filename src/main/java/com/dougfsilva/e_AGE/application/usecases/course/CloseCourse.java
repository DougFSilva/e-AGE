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

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CloseCourse {

	private final CourseRepository repository;
	
	private final ClazzRepository clazzRepository;
	
	private final FindCourse findCourse;
	
	private StandardLogger logger;
	
	public CourseResponse execute(String ID, LocalDate date) {
		try {
			Course course = findCourse.findByID(ID);
			List<Clazz> clazzes = clazzRepository.findAllByCourse(course);
			if(clazzes.stream().anyMatch(clazz -> !clazz.getIsClosed())) {
				throw new CourseOperationException("It is not possible to close the course, as there are still open classes belonging to it!");
			}
			course.setIsClosed(true);
			course.setClosingDate(date);
			Course closedCourse = repository.save(course);
			logger.info(String.format("Closed Course ID %s - %s", closedCourse.getID(), closedCourse.getTitle()));
			return CourseResponse.fromCourse(closedCourse);
		} catch (Exception e) {
			logger.error("Unexpected error when closing course: " + e.getMessage());
			throw new CourseOperationException("Error while close course", e);
		}
		
	}
}
