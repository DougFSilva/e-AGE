package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.certificate.CertificateRepository;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseDeleter {

	private final CourseRepository repository;
	private final ImageStorageService imageService;
	private final ClazzRepository clazzRepository;
	private final CertificateRepository certificateRepository;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			Course course = repository.findByIdOrThrow(ID);
			ensureIsNoClazzRegisteredInTheCourseOrInTheCertification(course);
			repository.delete(course);
			imageService.deleteImage(ImageType.COURSE, ImageNameGenerator.byCourse(course));
			logger.info(String.format("Deleted Course ID %s - %s", course.getID(), course.getTitle()));
		} catch (ObjectNotFoundException | OperationNotAllowedException e) {
			String message = String.format("Error while deleting course ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new CourseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting course ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}
	
	private void ensureIsNoClazzRegisteredInTheCourseOrInTheCertification(Course course) {
		if (clazzRepository.existsByCourse(course) || certificateRepository.existsByCourse(course)) {
			throw new OperationNotAllowedException(String.format(
					"The Course %s cannot be deleted because there are classes or certificate still associated with it!",
					course.getTitle()));
		}
	}
}
