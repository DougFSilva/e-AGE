package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.request.ClazzDataRequest;
import com.dougfsilva.e_AGE.application.usecases.course.FindCourse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClazz {

	private final ClazzRepository repository;

	private final FindCourse findCourse;

	private final StoreImage storeImage;

	private final StandardLogger logger;

	public Clazz create(ClazzDataRequest request) {
		Course course = findCourse.findByID(request.courseID());
		String imageUrl = storeImage.execute(request.image(), ImageType.CLAZZ, request.code());
		Clazz clazz = new Clazz(request.code(), course, imageUrl);
		Clazz createdClazz = repository.save(clazz);
		logger.createdObjectLog(createdClazz);
		return createdClazz;
	}

}
