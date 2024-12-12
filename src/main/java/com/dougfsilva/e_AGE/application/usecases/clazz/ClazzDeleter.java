package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.ClazzValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzDeleter {

	private final ClazzRepository repository;
	private final ClazzFinder clazzFinder;
	private final ClazzValidator validator;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			Clazz clazz = clazzFinder.findByID(ID);
			validator.hasNoEnrollmentRegisteredInTheClazz(clazz);
			repository.delete(clazz);
			logger.info(String.format("Deleted class ID %s, code %s", clazz.getID(), clazz.getCode()));
		} catch (ObjectNotFoundException | ClazzValidationException e) {
			String message = String.format("Error while deleting class ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting class ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new ClazzOperationException(message, e);
		}
	}
}
