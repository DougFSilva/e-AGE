package com.dougfsilva.e_AGE.application.usecases.student;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentImageUploader {
	
	private final StudentRepository repository;
	private final StudentFinder studentFinder;
	private final ImageStorageService imageService;
	private final StandardLogger logger;

	public StudentResponse upload(String ID, MultipartFile image) {
		try {
			if (image == null || image.isEmpty()) {
				throw new IllegalArgumentException("Image cannot be null or Empty");
			}
			Student student = studentFinder.findByID(ID);
			String imageUrl = imageService.uploadImage(image, ImageType.STUDENT, ImageNameGenerator.byStudent(student));
			student.setImage(imageUrl);
			Student updatedStudent = repository.save(student);
	        logger.info(String.format("Image uploaded successfully for student ID %s - %s ", student.getID(), student.getName()));
			return StudentResponse.fromStudent(updatedStudent);
		} catch (Exception e) {
			logger.error("Unexpected error when uploading employee image: " + e.getMessage());
			throw new ImageOperationException("Error while upload employee image", e);
		}
	}
}
