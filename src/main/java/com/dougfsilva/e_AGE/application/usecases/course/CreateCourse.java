package com.dougfsilva.e_AGE.application.usecases.course;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.UserDto;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCourse {

	private final CourseRepository repository;
	
	private final FindTechnologicalArea findTechnologicalArea;
	
	private final AuthChecker checker;
	
	private final Logger logger;
	
	public Course create(CourseModality modality, String title, String technologicalAreaID) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(technologicalAreaID);
		Course course = new Course(modality, title, technologicalArea);
		Course createdCourse = repository.create(course);
		logger.info(String.format("%S created by %S", createdCourse, new UserDto(checker.getUser())));
		return createdCourse;
	}
}
