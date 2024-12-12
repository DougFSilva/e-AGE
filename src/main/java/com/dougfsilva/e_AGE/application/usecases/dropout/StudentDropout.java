package com.dougfsilva.e_AGE.application.usecases.dropout;

import com.dougfsilva.e_AGE.application.dto.request.CreateDropoutRequest;
import com.dougfsilva.e_AGE.application.dto.response.DropoutResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.dropout.Dropout;
import com.dougfsilva.e_AGE.domain.dropout.DropoutRepository;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.exception.DropoutOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentDropout {

	private final DropoutRepository repository;
	private final EnrollmentRepository enrollmentRepository;
	private final StandardLogger logger;
	
	public DropoutResponse dropout(CreateDropoutRequest request) {
		try {
			Enrollment enrollment = enrollmentRepository.findByIdOrThrow(request.getEnrollmentID());
			updateEnrollmentStatus(enrollment);
			Dropout dropout = new Dropout(enrollment.getStudent(), enrollment.getClazz(), request.getReason(), request.getDate());
			Dropout createdDropout = repository.save(dropout);
			logger.info(String.format("Student %s dropped out of class %s",createdDropout.getStudent().getName(), createdDropout.getClazz().getCode()));
			return DropoutResponse.fromDropout(createdDropout);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while dropping student with enrollment ID %s : %s", request.getEnrollmentID(), e.getMessage());
			logger.warn(message, e);
			throw new DropoutOperationException(message, e);
		} catch (Exception e) {
			String message = String.format(
					"Unexpected error when dropping out student with enrollment ID %s : %s", request.getEnrollmentID(), e.getMessage());
			logger.error(message, e);
			throw new DropoutOperationException(message, e);
		}
	}
	
	private void updateEnrollmentStatus(Enrollment enrollment) {
		enrollment.setStatus(EnrollmentStatus.DROPPED);
		enrollmentRepository.save(enrollment);
	}
}
