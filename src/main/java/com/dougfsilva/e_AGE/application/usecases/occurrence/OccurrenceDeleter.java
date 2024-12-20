package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.exception.UnauthorizedUserException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceDeleter {

	private final OccurrenceRepository repository;
	private final OccurrenceOperationValidator validator;
	private final StandardLogger logger;
	
	public void delete(String ID) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			validator.ensureIsStatus(occurrence, OccurrenceStatus.OPEN);
			validator.ensureIsReporter(occurrence);
			repository.delete(occurrence);
			logger.info(String.format("Deleted occurrence ID %S", ID));
		} catch (ObjectNotFoundException | OperationNotAllowedException | UnauthorizedUserException e) {
			String message = String.format("Error while deleting occurrence ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting occurrence ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}
}
