package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.request.CreateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCourse {

	private final CourseRepository repository;

	private final FindTechnologicalArea findTechnologicalArea;

	private final StoreImage storeImage;
	
	private final CourseValidator validator;

	private final StandardLogger logger;

	public CourseResponse create(CreateCourseRequest request) {
		try {
			validator.validateUniqueTitle(request.getTitle());
			String imageUrl = storeImage.execute(request.getImage(), ImageType.COURSE, request.getTitle());
			TechnologicalArea technologicalArea = findTechnologicalArea.findByID(request.getTechnologicalAreaID());
			Course course = new Course(request.getModality(), request.getTitle(), technologicalArea, imageUrl, false, request.getCreationDate());
			Course createdCourse = repository.save(course);
			logger.info(String.format("Created Course ID %s - %s", createdCourse.getID(), createdCourse.getTitle()));
			return CourseResponse.fromCourse(createdCourse);
		} catch (Exception e) {
			logger.error("Unexpected error when creating course: " + e.getMessage());
			throw new CourseOperationException("Error while create course", e);
		}
		
	}
}
