package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Disenroller {

	private final EnrollmentRepository repository;
	private final StandardLogger logger;
	
	public void disenrollByID(String ID) {
		try {
			Enrollment enrollment = repository.findByIdOrThrow(ID);
			repository.delete(ID);
			logger.info(String.format("Student %s disenrolled in class %s", enrollment.getStudent().getName(), enrollment.getClazz().getCode()));
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while deleting enrollment ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new EnrollmentOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting enrollment ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new EnrollmentOperationException(message, e);
		}
	}
}
