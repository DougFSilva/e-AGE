package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Disenroll {

	private final EnrollmentRepository repository;
	
	private final FindEnrollment findEnrollment;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		try {
			Enrollment enrollment = findEnrollment.findByID(ID);
			repository.delete(ID);
			logger.info(String.format("Student %s disenrolled in Class %s", enrollment.getStudent().getName(), enrollment.getClazz().getCode()));
		} catch (Exception e) {
			logger.error("Unexpected error when disenrolling student: " + e.getMessage());
			throw new EnrollmentOperationException("Error while disenroll student", e);
		}
	
	}
}
