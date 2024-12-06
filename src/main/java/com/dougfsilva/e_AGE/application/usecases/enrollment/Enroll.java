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
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Enroll {

	private final EnrollmentRepository repository;
	
	private final FindStudent findStudent;
	
	private final FindClazz findClazz;
	
	private StandardLogger logger;
	
	public EnrollmentResponse executa(EnrollRequest request) {
		if(repository.existsByRegistration(request.getRegistration())) {
			throw new DataIntegrityViolationException(String.format("Enrollment with registration %S already exists", request.getRegistration()));
		}
		Student student = findStudent.findByID(request.getStudentID());
		Clazz clazz = findClazz.findByID(request.getClazzID());
		Enrollment enrollment = new Enrollment(request.getRegistration(), student, clazz, request.getDate(), EnrollmentStatus.ENROLLED);
		Enrollment createEnrollment = repository.save(enrollment);
		logger.enrollLog(student, clazz);
		return EnrollmentResponse.fromEnrollment(createEnrollment);
		
	}
}
