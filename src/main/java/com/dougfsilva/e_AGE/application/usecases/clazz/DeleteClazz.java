package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteClazz {

	private final ClazzRepository repository;

	private final FindClazz findClazz;
	
	private final ClazzValidator validator;

	private final StandardLogger logger;

	public void execute(String ID) {
		try {
			Clazz clazz = findClazz.findByID(ID);
			validator.validateNoEnrollmentRegisteredInTheClazz(clazz);
			repository.delete(clazz);
			logger.info(String.format("Delete Class ID %s - %s", clazz.getID(), clazz.getCode()));
		} catch (Exception e) {
			logger.error("Unexpected error when deleting class: " + e.getMessage());
			throw new ClazzOperationException("Error while delete class", e);
		}

	}

}
