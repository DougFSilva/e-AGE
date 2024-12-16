package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.dto.request.UpdateOccurrenceRequest;
import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceValidationException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.exception.UnauthorizedUserException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceUpdater {

	private final OccurrenceRepository repository;
	private final UserContext userContext;
	private final StandardLogger logger;

	public OccurrenceResponse update(UpdateOccurrenceRequest request) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(request.getID());
			ensureIsReporter(occurrence);
			ensureIsOpen(occurrence);
			ensuresIsNotSigned(occurrence);
			Occurrence updatedOccurrence = updateOccurrenceData(occurrence, request);
			Occurrence savedOccurrence = repository.save(updatedOccurrence);
			logger.info(String.format("Updated occurrence ID %s of student %s", savedOccurrence.getID(),
					savedOccurrence.getStudent().getName()));
			return OccurrenceResponse.fromOccurrence(savedOccurrence);
		} catch (ObjectNotFoundException | OccurrenceValidationException | UnauthorizedUserException | OperationNotAllowedException e) {
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
		if (request.getResolutionDescription() != null && !request.getResolutionDescription().isBlank()) {
			occurrence.setResolutionDescription(request.getResolutionDescription());
		}
		return occurrence;
	}

	private void ensureIsOpen(Occurrence occurrence) {
		if (!occurrence.getOpen()) {
			throw new OperationNotAllowedException(
					String.format("occurrence %s is closed, therefore it cannot be edited", occurrence.getID()));
		}
	}

	private void ensuresIsNotSigned(Occurrence occurrence) {
		if (occurrence.getStudentSignature() != null && !occurrence.getStudentSignature().isBlank()) {
			throw new OperationNotAllowedException(
					String.format("occurrence %s is signed, therefore it cannot be edited", occurrence.getID()));
		}
	}

	private void ensureIsReporter(Occurrence occurrence) {
		if (occurrence.getReporter().getUser() != userContext.getCurrentUserOrThrow()) {
			throw new UnauthorizedUserException("Only the occurrence reporter can edit it");
		}
	}
}
