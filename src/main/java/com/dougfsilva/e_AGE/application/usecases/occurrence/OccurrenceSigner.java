package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceSigner {

	private final OccurrenceRepository repository;
	private final StandardLogger logger;
	
	public OccurrenceResponse sign(String ID, String sign) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			ensureIsOpenOccurrence(occurrence);
			occurrence.setStudentSignature(sign);
			Occurrence savedOccurrence = repository.save(occurrence);
			return OccurrenceResponse.fromOccurrence(savedOccurrence);
		} catch (OperationNotAllowedException e) {
			String message = String.format("Error while signing occurrence ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when signing occurrence ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}
	
	private void ensureIsOpenOccurrence(Occurrence occurrence) {
		if(!occurrence.getOpen()) {
			throw new OperationNotAllowedException(String.format("Occurrence with ID %s is closed!", occurrence.getID()));
		}
	}
}
