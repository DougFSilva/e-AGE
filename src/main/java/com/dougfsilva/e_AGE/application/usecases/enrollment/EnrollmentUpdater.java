package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.application.dto.request.UpdateEnrollmentRequest;
import com.dougfsilva.e_AGE.application.dto.response.EnrollmentResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentOperationException;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnrollmentUpdater {

	private final EnrollmentRepository repository;
	private final EnrollmentValidator validator;
	private final StandardLogger logger;
	
	public EnrollmentResponse update(UpdateEnrollmentRequest request) {
		try {
			Enrollment enrollment = repository.findByIdOrThrow(request.getID());
			Enrollment updatedEnrollment = updateEnrollmentData(enrollment, request);
			Enrollment savedEnrollment = repository.save(updatedEnrollment);
			logger.info(String.format("Updated Enrollment ID %s", request.getID()));
			return EnrollmentResponse.fromEnrollment(savedEnrollment);
		} catch (ObjectNotFoundException | EnrollmentValidationException e) {
			String message = String.format("Error while updating enrollment ID %s : %s", request.getID(), e.getMessage());
			logger.warn(message, e);
			throw new EnrollmentOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when updating enrollment ID  %s : %s", request.getID(), e.getMessage());
			logger.error(message, e);
			throw new EnrollmentOperationException(message, e);
		}
	}
	
	private Enrollment updateEnrollmentData(Enrollment enrollment, UpdateEnrollmentRequest request) {
		if (request.getRegistration() != null 
				&& !request.getRegistration().isBlank() 
				&& !request.getRegistration().equalsIgnoreCase(enrollment.getRegistration())) {
			validator.uniqueRegistration(request.getRegistration());
			enrollment.setRegistration(request.getRegistration());
		}
		if (request.getDate() != null) {
			enrollment.setDate(request.getDate());
		}
		return enrollment;
	}
}
