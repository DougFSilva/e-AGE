package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.application.dto.request.EnrollRequest;
import com.dougfsilva.e_AGE.application.dto.response.EnrollmentResponse;
import com.dougfsilva.e_AGE.application.usecases.clazz.FindClazz;
import com.dougfsilva.e_AGE.application.usecases.student.FindStudent;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentOperationException;
import com.dougfsilva.e_AGE.domain.student.Student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Enroll {

	private final EnrollmentRepository repository;
	
	private final FindStudent findStudent;
	
	private final FindClazz findClazz;
	
	private StandardLogger logger;
	
	public EnrollmentResponse executa(EnrollRequest request) {
		try {
			validateUniqueRegistration(request.getRegistration());
			Student student = findStudent.findByID(request.getStudentID());
			Clazz clazz = findClazz.findByID(request.getClazzID());
			validateHasStudentEnrollment(student, clazz);
			Enrollment enrollment = new Enrollment(request.getRegistration(), student, clazz, request.getDate(), EnrollmentStatus.ENROLLED);
			Enrollment createEnrollment = repository.save(enrollment);
			logger.info(String.format("Student %s enrolled in Class %s", student.getName(), clazz.getCode()));
			return EnrollmentResponse.fromEnrollment(createEnrollment);
		} catch (Exception e) {
			logger.error("Unexpected error when enrolling student: " + e.getMessage());
			throw new EnrollmentOperationException("Error while enroll student", e);
		}
		
	}
	
	private void validateUniqueRegistration(String registration) {
		if(repository.existsByRegistration(registration)) {
			throw new EnrollmentOperationException(String.format("Enrollment with registration %s already exists!", registration));
		}
	}
	
	private void validateHasStudentEnrollment(Student student, Clazz clazz) {
		if(repository.existsByClazzAndStudent(student, clazz)) {
			throw new EnrollmentOperationException(String.format("The student %s is already enrolled in the class %s!", student.getName(), clazz.getCode()));
		}
	}
}
