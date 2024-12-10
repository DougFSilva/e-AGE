package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.domain.certificate.CertificateRepository;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseValidator {

	private final CourseRepository repository;
	private final ClazzRepository clazzRepository;
	private final CertificateRepository certificateRepository;

	public void uniqueTitle(String title) {
		if(repository.existsByTitle(title)) {
			throw new CourseOperationException(String.format("Course with title %s already exists!", title));
		}
	}
	
	public void hasNoClazzRegisteredInTheCourseOrInTheCertification(Course course) {
		if (clazzRepository.existsByCourse(course) || certificateRepository.existsByCourse(course)) {
			throw new DataIntegrityViolationException(String.format(
					"The Course %s cannot be deleted because there are classes or certificate still associated with it!",
					course.getTitle()));
		}
	}
}
