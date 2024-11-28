package com.dougfsilva.e_AGE.application.usecases.course;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.UserDto;
import com.dougfsilva.e_AGE.application.dto.request.UpdateCourseRequest;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCourse {

	private final CourseRepository repository;
	
	private final FindTechnologicalArea findTechnologicalArea;
	
	private final FindCourse findCourse;
	
	private final AuthChecker checker;
	
	private final Logger logger;
	
	public Course update(UpdateCourseRequest request) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(request.technologicalAreaID());
		Course course = findCourse.findByID(request.ID());
		course.setModality(request.modality());
		course.setTitle(request.title());
		course.setTechnologicalArea(technologicalArea);
		Course updatedCourse = repository.save(course);
		logger.info(String.format("%S updated by %S", updatedCourse, new UserDto(checker.getUser())));
		return updatedCourse;
	}
}
