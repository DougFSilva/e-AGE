package com.dougfsilva.e_AGE.application.usecases.course;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CloseCourse {

	private final CourseRepository repository;
	
	private final ClazzRepository clazzRepository;
	
	private final FindCourse findCourse;
	
	private StandardLogger logger;
	
	public CourseResponse execute(String ID, LocalDate date) {
		Course course = findCourse.findByID(ID);
		List<Clazz> clazzes = clazzRepository.findAllByCourse(course);
		if(clazzes.stream().anyMatch(clazz -> !clazz.getIsClosed())) {
			throw new DataIntegrityViolationException("It is not possible to close the course, as there are still open classes belonging to it!");
		}
		course.setIsClosed(true);
		course.setClosingDate(date);
		Course closedCourse = repository.save(course);
		logger.closingLog(closedCourse);
		return CourseResponse.fromCourse(closedCourse);
	}
}
