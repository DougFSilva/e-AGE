package com.dougfsilva.e_AGE.application.usecases.course;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCourse {

	private final CourseRepository repository;
	
	private final AuthChecker checker;
	
	public Course findByID(String ID) {
		checker.requireAnyProfiles(getClass());
		Optional<Course> course = repository.findByID(ID);
		return course.orElseThrow(() -> new ObjectNotFoundException(String.format("Course with ID %S not found!", ID)));
	}
}
