package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceDeleter {

	private final OccurrenceRepository repository;
	private final StandardLogger logger;
	
	public void delete(String ID) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			repository.delete(occurrence);
			logger.info(String.format("Deleted occurrence ID %S", ID));
		}  catch (ObjectNotFoundException e) {
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
