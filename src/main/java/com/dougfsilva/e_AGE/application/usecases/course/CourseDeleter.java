package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseDeleter {

	private final CourseRepository repository;
	private final CourseFinder courseFinder;
	private final ImageStorageService imageService;
	private final CourseValidator validator;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			Course course = courseFinder.findByID(ID);
			validator.hasNoClazzRegisteredInTheCourseOrInTheCertification(course);
			repository.delete(course);
			imageService.deleteImage(ImageType.COURSE, ImageNameGenerator.byCourse(course));
			logger.info(String.format("Deleted Course ID %s - %s", course.getID(), course.getTitle()));
		} catch (Exception e) {
			logger.error("Unexpected error when deleting course: " + e.getMessage());
			throw new CourseOperationException("Error while update course", e);
		}

	}
}
