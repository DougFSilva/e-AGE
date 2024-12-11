package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseImageDeleter {

	private final CourseRepository repository;
	private final CourseFinder courseFinder;
	private final ImageStorageService  imageService;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			Course course = courseFinder.findByID(ID);
			deleteImage(course);
			logger.info(String.format("Image deleted successfully for course ID %s, %s ", course.getID(), course.getTitle()));
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while delete course image ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting course image ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new ClazzOperationException(message, e);
		}
	}
	
	private void deleteImage(Course course) {
		imageService.deleteImage(ImageType.COURSE, ImageNameGenerator.byCourse(course));
		course.setImage(null);
		repository.save(course);
	}
}
