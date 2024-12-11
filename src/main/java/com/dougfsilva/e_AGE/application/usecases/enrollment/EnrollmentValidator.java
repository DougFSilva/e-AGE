package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentValidatorException;
import com.dougfsilva.e_AGE.domain.student.Student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnrollmentValidator {

	private final EnrollmentRepository repository;
	
	public void uniqueRegistration(String registration) {
		if(repository.existsByRegistration(registration)) {
			throw new EnrollmentValidatorException(String.format("Enrollment with registration %s already exists!", registration));
		}
	}
	
	public void studentNotEnrolledInClazz(Student student, Clazz clazz) {
		if(repository.existsByClazzAndStudent(student, clazz)) {
			throw new EnrollmentValidatorException(String.format("The student %s is already enrolled in the class %s!", student.getName(), clazz.getCode()));
		}
	}
	
	public void clazzIsNotClosed(Clazz clazz) {
		if(clazz.getIsClosed()) {
			throw new EnrollmentValidatorException("It is not possible to enroll a student in a closed class");
		}
	}
}
