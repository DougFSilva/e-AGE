package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.exception.UnauthorizedUserException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceStatus;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceOperationValidator {

	private final UserContext userContext;
	
	public void ensureIsUserManagement(String cause) {
		if (!userContext.getCurrentUserOrThrow().checkContainsProfile(ProfileType.MANAGEMENT)) {
			throw new UnauthorizedUserException("A management profile is required to perform this operation. Reason: " + cause);
		}
	}
	
	public void ensureIsReporter(Occurrence occurrence) {
		if (occurrence.getReporter().getUser() != userContext.getCurrentUserOrThrow()) {
			throw new UnauthorizedUserException("Only the reporter of the occurrence can perform this operation!");
		}
	}
	
	public void ensureIsReported(Occurrence occurrence) {
		if (occurrence.getStudent().getUser() != userContext.getCurrentUserOrThrow()) {
			throw new UnauthorizedUserException("This operation can only be performed by the student reported in the occurrence!");
		}
	}
	
	public void ensureIsStatus(Occurrence occurrence, OccurrenceStatus status) {
		if (occurrence.getStatus() != status) {
			throw new OperationNotAllowedException("This operation can only be performed if the occurrence status is " + status.name());
		}
	}
	
	public void ensureIsNotForwarded(Occurrence occurrence) {
		if (occurrence.getForwarded()) {
			throw new OperationNotAllowedException("This operation can only be performed if the occurrence has not been forwarded!");
		}
	}
	
	public void ensureIsSigned(Occurrence occurrence) {
		String signature = occurrence.getStudentSignature().getSignature();
		if (signature == null || signature.isBlank()) {
			throw new UnauthorizedUserException("This operation can only be performed if the occurrence is signed!");
		}
	}
	
}
