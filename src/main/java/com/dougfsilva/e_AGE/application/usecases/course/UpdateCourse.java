package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.request.CourseDataRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCourse {

	private final CourseRepository repository;
	
	private final FindTechnologicalArea findTechnologicalArea;
	
	private final StoreImage storeImage;
	
	private final FindCourse findCourse;
	
	private final StandardLogger logger;
	
	public CourseResponse execute(String ID, CourseDataRequest request) {
		Course course = findCourse.findByID(ID);
		if(request.technologicalAreaID() != null && !request.technologicalAreaID().isBlank()) {
			TechnologicalArea technologicalArea = findTechnologicalArea.findByID(request.technologicalAreaID());
			course.setTechnologicalArea(technologicalArea);
		}
		if(request.image() != null && !request.image().isEmpty()) {
			String imageUrl = storeImage.execute(request.image(), ImageType.COURSE, request.title());
			course.setImage(imageUrl);
		}
		if(request.modality() != null) {
			course.setModality(request.modality());
		}
		if(request.title() != null && !request.title().isBlank()) {
			course.setTitle(request.title());
		}
		Course updatedCourse = repository.save(course);
		logger.updatedObjectLog(updatedCourse);
		return CourseResponse.fromCourse(updatedCourse);
	}
}
