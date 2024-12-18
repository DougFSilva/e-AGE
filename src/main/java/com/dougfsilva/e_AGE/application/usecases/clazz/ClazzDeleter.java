package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.dropout.DropoutRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzDeleter {

	private final ClazzRepository repository;
	private final EnrollmentRepository enrollmentRepository;
	private final DropoutRepository dropoutRepository;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			Clazz clazz = repository.findByIdOrThrow(ID);
			ensureIsNoEnrollmentRegisteredInTheClazz(clazz);
			repository.delete(clazz);
			logger.info(String.format("Deleted class ID %s, code %s", clazz.getID(), clazz.getCode()));
		} catch (ObjectNotFoundException | OperationNotAllowedException e) {
			String message = String.format("Error while deleting class ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting class ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new ClazzOperationException(message, e);
		}
	}
	
	private void ensureIsNoEnrollmentRegisteredInTheClazz(Clazz clazz) {
		if (enrollmentRepository.existsByClazz(clazz) || dropoutRepository.existsByClazz(clazz)) {
			throw new OperationNotAllowedException(String.format(
					"The Class %s cannot be deleted because there are Students still associated with it!",
					clazz.getCode()));
		}
	}
}
