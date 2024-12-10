package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentImageDeleter {

	private final StudentRepository repository;
	private final StudentFinder studentFinder;
	private final ImageStorageService  imageService;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			Student student = studentFinder.findByID(ID);
			imageService.deleteImage(ImageType.STUDENT, ImageNameGenerator.byStudent(student));
			student.setImage(null);
			repository.save(student);
			logger.info(String.format("Image deleted successfully for student ID %s - %s ", student.getID(), student.getName()));
		} catch (Exception e) {
			logger.error("Unexpected error when deleting student image: " + e.getMessage());
			throw new ImageOperationException("Error while delete stundent image", e);
		}
	}
}
