package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.usecases.utilities.DeleteImage;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCourse {

	private final CourseRepository repository;

	private final FindCourse findCourse;

	private final DeleteImage deleteImage;
	
	private final CourseValidator validator;

	private final StandardLogger logger;

	public void execute(String ID) {
		try {
			Course course = findCourse.findByID(ID);
			validator.validateNoClazzRegisteredInTheCourseOrCertification(course);
			repository.delete(course);
			deleteImage.execute(course.getImage(), ImageType.COURSE);
			logger.info(String.format("Deleted Course ID %s - %s", course.getID(), course.getTitle()));
		} catch (Exception e) {
			logger.error("Unexpected error when deleting adddress: " + e.getMessage());
			throw new CourseOperationException("Error while update the address", e);
		}

	}
}
