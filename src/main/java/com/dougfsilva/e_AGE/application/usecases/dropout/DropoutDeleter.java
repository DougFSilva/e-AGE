package com.dougfsilva.e_AGE.application.usecases.dropout;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.dropout.Dropout;
import com.dougfsilva.e_AGE.domain.dropout.DropoutRepository;
import com.dougfsilva.e_AGE.domain.exception.DropoutOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DropoutDeleter {

	private final DropoutRepository repository;
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			Dropout dropout = repository.findByIdOrThrow(ID);
			repository.delete(dropout);
			logger.info(String.format("Deleted Dropout ID %s", ID));
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while deleting dropout ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new DropoutOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting dropout ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new DropoutOperationException(message, e);
		}
	}
}
