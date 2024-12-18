package com.dougfsilva.e_AGE.application.usecases.student;

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
public class StudentImageDeleter {

	private final StudentRepository repository;
	private final ImageStorageService  imageService;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			Student student = repository.findByIdOrThrow(ID);
			imageService.deleteImage(ImageType.STUDENT, ImageNameGenerator.byStudent(student));
			student.setImage(imageService.getDefaultImage(ImageType.STUDENT));
			repository.save(student);
			logger.info(String.format("Image deleted successfully for student ID %s - %s ", student.getID(), student.getName()));
		} catch (ObjectNotFoundException | ImageOperationException e) {
			String message = String.format("Error while deleting image of student ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new StudentOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting student image ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new StudentOperationException(message, e);
		}
	}
}
