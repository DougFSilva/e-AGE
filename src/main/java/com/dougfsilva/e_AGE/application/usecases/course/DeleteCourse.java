package com.dougfsilva.e_AGE.application.usecases.course;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.UserDto;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCourse {

	private final CourseRepository repository;
	
	private final FindCourse findCourse;
	
	private final AuthChecker checker;
	
	private final Logger logger;
	
	public void delete(String ID) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		Course course = findCourse.findByID(ID);
		repository.delete(course);
		logger.info(String.format("%S deleted by %S", course, new UserDto(checker.getUser())));
	}
}
