package com.dougfsilva.e_AGE.application.usecases.course;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseImageUploader {

	private final CourseRepository repository;
	private final ImageStorageService imageService;
	private final StandardLogger logger;

	public CourseResponse upload(String ID, MultipartFile image) {
		try {
			validateImage(image);
			Course course = repository.findByIdOrThrow(ID);
			String imageUrl = imageService.uploadImage(image, ImageType.COURSE, ImageNameGenerator.byCourse(course));
			course.setImage(imageUrl);
			Course savedCourse = repository.save(course);
			logger.info(String.format("Image uploaded successfully for course ID %s - %s ", savedCourse.getID(),
					savedCourse.getTitle()));
			return CourseResponse.fromCourse(savedCourse);
		} catch (ObjectNotFoundException | IllegalArgumentException | ImageOperationException e) {
			String message = String.format("Error while uploading course image ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new CourseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when uploading course image ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}

	private void validateImage(MultipartFile image) {
		if (image == null || image.isEmpty()) {
			throw new IllegalArgumentException("Image cannot be null or Empty");
		}
	}
}
