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
public class OccurrenceTreatmentUpdater {

	private final OccurrenceRepository repository;
	private final OccurrenceOperationValidator validator;
	private final StandardLogger logger;
	
	public OccurrenceResponse updater(String ID, String treatmentDescription) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			validator.ensureIsStatus(occurrence, OccurrenceStatus.OPEN);
			validateUserPermission(occurrence);
			occurrence.setTreatmentDescription(treatmentDescription);
			Occurrence savedOccurrence = repository.save(occurrence);
			logger.info(String.format("Updated treatment of occurrence ID %s", savedOccurrence.getID()));
			return OccurrenceResponse.fromOccurrence(savedOccurrence);
		}  catch (ObjectNotFoundException | UnauthorizedUserException | OperationNotAllowedException e) {
			String message = String.format("Error while treating occurrence to student ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when treating occurrence to student ID %s : %s", ID, e.getMessage());
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
