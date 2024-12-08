package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReopenCourse {

	private final CourseRepository repository;
	
	private final FindCourse findCourse;
	
	private final StandardLogger logger;
	
	public CourseResponse execute(String ID) {
		Course course = findCourse.findByID(ID);
		course.setClosingDate(null);
		course.setIsClosed(false);
		Course reopenCourse = repository.save(course);
		logger.info(String.format("Reopened Course ID %S - %S", reopenCourse.getID(), reopenCourse.getTitle()));
		return CourseResponse.fromCourse(reopenCourse);
	}
}
