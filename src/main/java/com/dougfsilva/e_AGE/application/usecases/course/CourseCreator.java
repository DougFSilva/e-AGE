package com.dougfsilva.e_AGE.application.usecases.course;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.application.dto.request.CreateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.CourseValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseCreator {

	private final CourseRepository repository;
	private final TechnologicalAreaRepository technologicalAreaRepository;
	private final ImageStorageService imageService;
	private final CourseValidator validator;
	private final StandardLogger logger;

	public CourseResponse create(CreateCourseRequest request) {
		try {
			validator.uniqueTitle(request.getTitle());
			Course course = buildCourse(request);
			Course savedCourse = repository.save(course);
			logger.info(String.format("Created Course ID %s, %s", savedCourse.getID(), savedCourse.getTitle()));
			return CourseResponse.fromCourse(savedCourse);
		} catch (ObjectNotFoundException | CourseValidationException e) {
			String message = String.format("Error while creating course %s : %s", request.getTitle(), e.getMessage());
			logger.warn(message, e);
			throw new CourseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating course %s : %s", request.getTitle(), e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}

	private Course buildCourse(CreateCourseRequest request) {
		TechnologicalArea technologicalArea = technologicalAreaRepository.findByIdOrThrow(request.getTechnologicalAreaID());
		return new Course(
				request.getModality(), 
				request.getTitle(), 
				technologicalArea, 
				false, 
				LocalDate.now(),
				imageService.getDefaultImage(ImageType.COURSE));
	}
}
