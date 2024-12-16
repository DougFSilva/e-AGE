package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceValidationException;
import com.dougfsilva.e_AGE.domain.exception.UnauthorizedUserException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceDeleter {

	private final OccurrenceRepository repository;
	private final UserContext userContext;
	private final StandardLogger logger;
	
	public void delete(String ID) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			ensureIsReporter(occurrence);
			repository.delete(occurrence);
			logger.info(String.format("Deleted occurrence ID %S", ID));
		}  catch (ObjectNotFoundException | OccurrenceValidationException | UnauthorizedUserException e) {
			String message = String.format("Error while deleting occurrence ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting occurrence ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}
	
	public void ensureIsReporter(Occurrence occurrence) {
		if (userContext.getCurrentUserOrThrow() != occurrence.getReporter().getUser()) {
			throw new UnauthorizedUserException("Only the occurrence reporter can delete it");
		}
	}
	
}
