package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceSignatureOperationException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceSignature;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceSignatureCryptography;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceSigner {

	private final OccurrenceRepository repository;
	private final OccurrenceSignatureCryptography signer;
	private final OccurrenceOperationValidator validator;
	private final StandardLogger logger;
	
	public OccurrenceResponse sign(String ID) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			validator.ensureIsStatus(occurrence, OccurrenceStatus.CLOSED);
			validator.ensureIsReported(occurrence);
			OccurrenceSignature signature = signer.generateSignature(occurrence);
			occurrence.setStudentSignature(signature);
			Occurrence savedOccurrence = repository.save(occurrence);
			return OccurrenceResponse.fromOccurrence(savedOccurrence);
		} catch (OperationNotAllowedException | OccurrenceSignatureOperationException e) {
			String message = String.format("Error while signing occurrence ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when signing occurrence ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}
	
}
