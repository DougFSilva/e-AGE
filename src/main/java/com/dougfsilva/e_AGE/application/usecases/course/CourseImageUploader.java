package com.dougfsilva.e_AGE.application.usecases.course;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseImageUploader {

	private final CourseRepository repository;
	private final CourseFinder courseFinder;
	private final ImageStorageService imageService;
	private final StandardLogger logger;

	public CourseResponse upload(String ID, MultipartFile image) {
		try {
			validateImage(image);
			Course course = courseFinder.findByID(ID);
			Course updatedCourse = uploadImage(course, image);
			logger.info(String.format("Image uploaded successfully for course ID %s - %s ", updatedCourse.getID(),
					updatedCourse.getTitle()));
			return CourseResponse.fromCourse(updatedCourse);
		} catch (ObjectNotFoundException | IllegalArgumentException e) {
			String message = String.format("Error while upload course image ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new CourseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when uploading course image ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}

	private Course uploadImage(Course course, MultipartFile image) {
		String imageUrl = imageService.uploadImage(image, ImageType.COURSE, ImageNameGenerator.byCourse(course));
		course.setImage(imageUrl);
		return repository.save(course);
	}

	private void validateImage(MultipartFile image) {
		if (image == null || image.isEmpty()) {
			throw new IllegalArgumentException("Image cannot be null or Empty");
		}
	}
}
