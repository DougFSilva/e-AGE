package com.dougfsilva.e_AGE.application.usecases.student;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.StudentOperationException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentImageUploader {
	
	private final StudentRepository repository;
	private final ImageStorageService imageService;
	private final StandardLogger logger;

	public StudentResponse upload(String ID, MultipartFile image) {
		try {
			validateImage(image);
			Student student = repository.findByIdOrThrow(ID);
			String imageUrl = imageService.uploadImage(image, ImageType.STUDENT, ImageNameGenerator.byStudent(student));
			student.setImage(imageUrl);
			Student savedStudent = repository.save(student);
	        logger.info(String.format("Image uploaded successfully for student ID %s - %s ", student.getID(), student.getName()));
			return StudentResponse.fromStudent(savedStudent);
		} catch (ObjectNotFoundException | IllegalArgumentException | ImageOperationException e) {
			String message = String.format("Error while uploading employee image ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new StudentOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when uploading student image ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new StudentOperationException(message, e);
		}
	}
	

	private void validateImage(MultipartFile image) {
		if (image == null || image.isEmpty()) {
			throw new IllegalArgumentException("Image cannot be null or Empty");
		}
	}
}
