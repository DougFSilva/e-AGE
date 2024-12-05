package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.usecases.utilities.DeleteImage;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.certificate.CertificateRepository;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCourse {

	private final CourseRepository repository;
	
	private final ClazzRepository clazzRepository;
	
	private final CertificateRepository certificateRepository;
	
	private final FindCourse findCourse;
	
	private final DeleteImage deleteImage;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		Course course = findCourse.findByID(ID);
		if(clazzRepository.existsByCourse(course) || certificateRepository.existsByCourse(course)) {
			throw new DataIntegrityViolationException(
					String.format("The Course %S cannot be deleted because there are classes or certificate still associated with it!", 
							course.getTitle()));
		}
		repository.delete(course);
		deleteImage.execute(course.getImage(), ImageType.COURSE);
		logger.deletedObjectLog(course);
	}
}
