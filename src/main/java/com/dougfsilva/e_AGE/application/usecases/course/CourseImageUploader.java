package com.dougfsilva.e_AGE.application.usecases.course;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
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
			if (image == null || image.isEmpty()) {
				throw new IllegalArgumentException("Image cannot be null or Empty");
			}
			Course course = courseFinder.findByID(ID);
			String imageUrl = imageService.uploadImage(image, ImageType.COURSE,
					ImageNameGenerator.byCourse(course));
			course.setImage(imageUrl);
			Course updatedCourse = repository.save(course);
	        logger.info(String.format("Image uploaded successfully for course ID %s - %s ", course.getID(), course.getTitle()));
			return CourseResponse.fromCourse(updatedCourse);
		} catch (Exception e) {
			logger.error("Unexpected error when uploading course image: " + e.getMessage());
			throw new ImageOperationException("Error while upload course image", e);
		}
	}
}
