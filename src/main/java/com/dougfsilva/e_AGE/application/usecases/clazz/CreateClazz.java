package com.dougfsilva.e_AGE.application.usecases.clazz;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.UserDto;
import com.dougfsilva.e_AGE.application.dto.request.CreateClazzRequest;
import com.dougfsilva.e_AGE.application.usecases.course.FindCourse;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClazz {
	
	private final ClazzRepository repository;
	
	private final FindCourse findCourse;
	
	private final ImageStorageService imageStorageService;
	
	private final AuthChecker checker;
	
	private final Logger logger;
	
	public Clazz create(CreateClazzRequest request) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		Course course = findCourse.findByID(request.courseID());
		String imageUrl = imageStorageService.storeImage(request.image());
		Clazz clazz = new Clazz(request.code(), course, request.name(), imageUrl);
		Clazz createdClazz = repository.save(clazz);
		logger.info(String.format("%S created by %S", createdClazz, new UserDto(checker.getUser())));
		return createdClazz;
	}

}
