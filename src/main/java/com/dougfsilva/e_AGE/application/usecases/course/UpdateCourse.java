package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.request.UpdateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;
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
		Course course = findCourse.findByID(request.getID());
		if(!request.getTitle().equalsIgnoreCase(course.getTitle()) && request.getTitle() != null && !request.getTitle().isBlank()) {
			repository.findByTitle(request.getTitle().toUpperCase()).ifPresent(c -> {
				throw new DataIntegrityViolationException(String.format("Course with title %S already exists!", request.getTitle()));
			});
			course.setTitle(request.getTitle());
		}
		if(request.getTechnologicalAreaID() != null && !request.getTechnologicalAreaID().isBlank()) {
			TechnologicalArea technologicalArea = findTechnologicalArea.findByID(request.getTechnologicalAreaID());
			course.setTechnologicalArea(technologicalArea);
		}
		if(request.getImage() != null && !request.getImage().isEmpty()) {
			String imageUrl = storeImage.execute(request.getImage(), ImageType.COURSE, request.getTitle());
			course.setImage(imageUrl);
		}
		if(request.getModality() != null) {
			course.setModality(request.getModality());
		}
		Course updatedCourse = repository.save(course);
		logger.info(String.format("Updated Course ID %S - %S", updatedCourse.getID(), updatedCourse.getTitle()));
		return CourseResponse.fromCourse(updatedCourse);
	}
}
