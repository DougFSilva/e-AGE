package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.request.UpdateCourseRequest;
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
	
	public CourseResponse execute(UpdateCourseRequest request) {
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(request.technologicalAreaID());
		String imageUrl = storeImage.execute(request.image(), ImageType.COURSE, request.title());
		Course course = findCourse.findByID(request.ID());
		course.setModality(request.modality());
		course.setTitle(request.title());
		course.setTechnologicalArea(technologicalArea);
		course.setImage(imageUrl);
		Course updatedCourse = repository.save(course);
		logger.updatedObjectLog(updatedCourse);
		return CourseResponse.fromCourse(updatedCourse);
	}
}
