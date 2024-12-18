package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
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
public class OccurrenceForwarder {

	private final OccurrenceRepository repository;
	private final OccurrenceOperationValidator validator;
	private final StandardLogger logger;
	
	public OccurrenceResponse forward(String ID) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			validator.ensureIsStatus(occurrence, OccurrenceStatus.OPEN);
			validator.ensureIsReporter(occurrence);
			occurrence.setForwarded(true);;
			Occurrence savedOccurrence = repository.save(occurrence);
			logger.info(String.format("Forwarded Occurrence ID %s", savedOccurrence.getID()));
			return OccurrenceResponse.fromOccurrence(occurrence);
		} catch (ObjectNotFoundException | UnauthorizedUserException | OperationNotAllowedException e) {
			String message = String.format("Error while forwarding occurrence to student ID %s : %s",ID, e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when forwarding occurrence to student ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}
	
}
