package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Disenroll {

	private final EnrollmentRepository repository;
	
	private final FindEnrollment findEnrollment;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		Enrollment enrollment = findEnrollment.findByID(ID);
		repository.delete(ID);
		logger.disenrollLog(enrollment.getStudent(), enrollment.getClazz());
	}
}
