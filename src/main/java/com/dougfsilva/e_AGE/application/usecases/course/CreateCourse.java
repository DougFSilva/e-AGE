package com.dougfsilva.e_AGE.application.usecases.course;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.request.CreateCourseRequest;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.Logger;
import com.dougfsilva.e_AGE.domain.utilities.StandardLogger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCourse {

	private final CourseRepository repository;
	
	private final FindTechnologicalArea findTechnologicalArea;
	
	private final ImageStorageService imageStorageService;
	
	private final AuthChecker checker;
	
	private final Logger logger;
	
	public Course create(CreateCourseRequest request) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		String imageUrl = imageStorageService.storeImage(request.image());
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(request.technologicalAreaID());
		Course course = new Course(request.modality(), request.title(), technologicalArea, imageUrl);
		Course createdCourse = repository.save(course);
		StandardLogger.createdObjectLogger(createdCourse, checker, logger);
		return createdCourse;
	}
}
