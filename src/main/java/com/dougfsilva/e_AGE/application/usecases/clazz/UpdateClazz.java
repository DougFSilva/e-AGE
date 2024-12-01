package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.request.UpdateClazzRequest;
import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.course.FindCourse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateClazz {

	private final ClazzRepository repository;
	
	private final FindClazz findClazz;
	
	private final FindCourse findCourse;
	
	private final StoreImage storeImage;
	
	private final StandardLogger logger;
	
	public ClazzResponse execute(UpdateClazzRequest request) {
		Course course = findCourse.findByID(request.courseID());
		String imageUrl = storeImage.execute(request.image(), ImageType.CLAZZ, request.code());
		Clazz clazz = findClazz.findByID(request.ID());
		clazz.setCode(request.code());
		clazz.setCourse(course);
		clazz.setImage(imageUrl);
		Clazz updatedClazz = repository.save(clazz);
		logger.updatedObjectLog(updatedClazz);
		return ClazzResponse.fromClazz(updatedClazz);
	}
	
}
