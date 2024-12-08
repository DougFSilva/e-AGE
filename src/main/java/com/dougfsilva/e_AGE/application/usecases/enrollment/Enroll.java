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
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;
import com.dougfsilva.e_AGE.domain.student.Student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Enroll {

	private final EnrollmentRepository repository;
	
	private final FindStudent findStudent;
	
	private final FindClazz findClazz;
	
	private StandardLogger logger;
	
	public EnrollmentResponse executa(EnrollRequest request) {
		if(repository.existsByRegistration(request.getRegistration())) {
			throw new DataIntegrityViolationException(String.format("Enrollment with registration %S already exists!", request.getRegistration()));
		}
		Student student = findStudent.findByID(request.getStudentID());
		Clazz clazz = findClazz.findByID(request.getClazzID());
		if(repository.existsByClazzAndStudent(student, clazz)) {
			throw new DataIntegrityViolationException(String.format("The student %S is already enrolled in the class %S!", student.getName(), clazz.getCode()));
		}
		Enrollment enrollment = new Enrollment(request.getRegistration(), student, clazz, request.getDate(), EnrollmentStatus.ENROLLED);
		Enrollment createEnrollment = repository.save(enrollment);
		logger.info(String.format("Student %S enrolled in Class %S", student.getName(), clazz.getCode()));
		return EnrollmentResponse.fromEnrollment(createEnrollment);
		
	}
}
