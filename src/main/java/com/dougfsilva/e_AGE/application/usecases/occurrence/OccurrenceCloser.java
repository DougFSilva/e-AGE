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
public class OccurrenceCloser {

	private final OccurrenceRepository repository;
	private final OccurrenceOperationValidator validator;
	private final StandardLogger logger;
	
	public OccurrenceResponse close(String ID) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			validator.ensureIsStatus(occurrence, OccurrenceStatus.OPEN);
			validateUserPermission(occurrence);
			occurrence.setStatus(OccurrenceStatus.CLOSED);
			Occurrence savedOccurrence = repository.save(occurrence);
			logger.info(String.format("Closed occurrence ID %S", savedOccurrence.getID()));
			return OccurrenceResponse.fromOccurrence(savedOccurrence);
		} catch (ObjectNotFoundException | UnauthorizedUserException | OperationNotAllowedException e) {
			String message = String.format("Error while closing occurrence to student ID %s : %s",ID, e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when closing occurrence to student ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}
	
	private void validateUserPermission(Occurrence occurrence) {
		if (occurrence.getForwarded()) {
			validator.ensureIsUserManagement("The occurrence has been forwarded!");
		} else {
			validator.ensureIsReporter(occurrence);
		} 
	}
}	