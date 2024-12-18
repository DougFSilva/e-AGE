package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.dto.request.UpdateOccurrenceRequest;
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
public class OccurrenceUpdater {

	private final OccurrenceRepository repository;
	private final OccurrenceOperationValidator validator;
	private final StandardLogger logger;

	public OccurrenceResponse update(UpdateOccurrenceRequest request) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(request.getID());
			validator.ensureIsStatus(occurrence, OccurrenceStatus.OPEN);
			validator.ensureIsReporter(occurrence);
			validator.ensureIsNotForwarded(occurrence);
			Occurrence updatedOccurrence = updateOccurrenceData(occurrence, request);
			Occurrence savedOccurrence = repository.save(updatedOccurrence);
			logger.info(String.format("Updated occurrence ID %s of student %s", savedOccurrence.getID(), savedOccurrence.getStudent().getName()));
			return OccurrenceResponse.fromOccurrence(savedOccurrence);
		} catch (ObjectNotFoundException | UnauthorizedUserException | OperationNotAllowedException e) {
			String message = String.format("Error while updating occurrence ID %s : %s", request.getID(), e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when updating occurrence ID %s : %s", request.getID(), e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}

	private Occurrence updateOccurrenceData(Occurrence occurrence, UpdateOccurrenceRequest request) {
		if (request.getCurricularUnit() != null && !request.getCurricularUnit().isBlank()) {
			occurrence.setCurricularUnit(request.getCurricularUnit());
		}
		if (request.getOccurrenceType() != null) {
			occurrence.setOccurrenceType(request.getOccurrenceType());
		}
		if (request.getRestricted() != null) {
			occurrence.setRestricted(request.getRestricted());
		}
		if (request.getDescription() != null && !request.getDescription().isBlank()) {
			occurrence.setDescription(request.getDescription());
		}
		return occurrence;
	}
}
