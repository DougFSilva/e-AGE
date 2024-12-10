package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.request.CreateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.TechnologicalAreaFinder;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseCreator {

	private final CourseRepository repository;
	private final TechnologicalAreaFinder findTechnologicalArea;
	private final ImageStorageService imageService;
	private final CourseValidator validator;
	private final StandardLogger logger;

	public CourseResponse create(CreateCourseRequest request) {
		try {
			validator.uniqueTitle(request.getTitle());
			TechnologicalArea technologicalArea = findTechnologicalArea.findByID(request.getTechnologicalAreaID());
			Course course = new Course(
					request.getModality(), 
					request.getTitle(), 
					technologicalArea, 
					false, 
					request.getCreationDate(), 
					imageService.getDefaultImage(ImageType.COURSE));
			Course createdCourse = repository.save(course);
			logger.info(String.format("Created Course ID %s - %s", createdCourse.getID(), createdCourse.getTitle()));
			return CourseResponse.fromCourse(createdCourse);
		}
		catch (Exception e) {
			logger.error("Unexpected error when creating course: " + e.getMessage());
			throw new CourseOperationException("Error while create course", e);
		}
		
	}
}
