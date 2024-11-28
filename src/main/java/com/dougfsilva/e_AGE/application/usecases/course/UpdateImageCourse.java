package com.dougfsilva.e_AGE.application.usecases.course;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.UserDto;
import com.dougfsilva.e_AGE.application.dto.request.UpdateImageCourseRequest;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateImageCourse {

	private final CourseRepository repository;
	
	private final FindCourse findCourse;
	
	private final ImageStorageService imageStorageService;
	
	private final AuthChecker checker;
	
	private final Logger logger;
	
	public Course update(UpdateImageCourseRequest request) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		Course course = findCourse.findByID(request.ID());
		String imageUrl = imageStorageService.storeImage(request.image());
		course.setImage(imageUrl);
		Course updatedCourse = repository.save(course);
		logger.info(String.format("%S updated by %S", updatedCourse, new UserDto(checker.getUser())));
		return updatedCourse;
	}
	
}
