package com.dougfsilva.e_AGE.application.usecases.dropout;

import com.dougfsilva.e_AGE.application.dto.request.CreateDropoutRequest;
import com.dougfsilva.e_AGE.application.dto.response.DropoutResponse;
import com.dougfsilva.e_AGE.application.usecases.enrollment.EnrollmentFinder;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.dropout.Dropout;
import com.dougfsilva.e_AGE.domain.dropout.DropoutRepository;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DropoutHandler {

	private final DropoutRepository repository;
	private final EnrollmentRepository enrollmentRepository;
	private final EnrollmentFinder enrollmentFinder;
	private final StandardLogger logger;
	
	public DropoutResponse dropout(CreateDropoutRequest request) {
		try {
			Enrollment enrollment = enrollmentFinder.findByID(request.getEnrollmentID());
			enrollment.setStatus(EnrollmentStatus.DROPPED);
			enrollmentRepository.save(enrollment);
			Dropout dropout = new Dropout(enrollment.getStudent(), enrollment.getClazz(), request.getReason(), request.getDate());
			Dropout createdDropout = repository.save(dropout);
			logger.info(String.format("Student %s dropped out of class %s",createdDropout.getStudent().getName(), createdDropout.getClazz().getCode()));
			return DropoutResponse.fromDropout(createdDropout);
		} catch (Exception e) {
			logger.error("Unexpected error when dropping out student: " + e.getMessage());
			throw new EnrollmentOperationException("Error while drop out student", e);
		}
	}
}
