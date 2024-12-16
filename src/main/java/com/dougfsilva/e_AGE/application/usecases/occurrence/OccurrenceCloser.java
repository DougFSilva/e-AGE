package com.dougfsilva.e_AGE.application.usecases.occurrence;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceValidationException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.exception.UnauthorizedUserException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceCloser {

	private final OccurrenceRepository repository;
	private final EmployeeRepository employeeRepository;
	private final UserContext userContext;
	private final StandardLogger logger;

	public OccurrenceResponse close(String ID) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			validateCloseOperation(occurrence);
			occurrence.setClosingDate(LocalDateTime.now());
			occurrence.setOpen(false);
			Employee employee = getClosureResponsible();
			occurrence.setClosureResponsible(employee);
			Occurrence savedOccurrence = repository.save(occurrence);
			logger.info(String.format("Closed occurrcence ID %s of the student %s", savedOccurrence.getID(),
					savedOccurrence.getStudent().getName()));
			return OccurrenceResponse.fromOccurrence(savedOccurrence);
		} catch (ObjectNotFoundException | OccurrenceValidationException |UnauthorizedUserException | OperationNotAllowedException e) {
			String message = String.format("Error while closing occurrence with ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when closing occurrence with ID %s : %s", ID,
					e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}

	private void validateCloseOperation(Occurrence occurrence) {
		ensureIsManagementUserOrReporter(occurrence);
		ensureIsManagementUserOrNonForwardedOccurrence(occurrence);
		ensureIsSigned(occurrence);
	}

	private void ensureIsManagementUserOrReporter(Occurrence occurrence) {
		if (!isUserManagement() && !isUserReporter(occurrence)) {
			throw new UnauthorizedUserException(String.format(
					"Operation not allowed. User ID %s does not have management profile and is not the reporter.",
					getAuthenticatedUser().getID()));
		}
	}

	private void ensureIsManagementUserOrNonForwardedOccurrence(Occurrence occurrence) {
		if (!isUserManagement() && occurrence.getForwarding()) {
			throw new UnauthorizedUserException(
					"Forwarded occurrences can only be closed by a user with a management profile.");
		}
	}

	private void ensureIsSigned(Occurrence occurrence) {
		if (occurrence.getStudentSignature() == null || occurrence.getStudentSignature().isBlank()) {
			throw new OperationNotAllowedException("An occurrence cannot be closed without the student's signature.");
		}
	}

	private Boolean isUserManagement() {
		return getAuthenticatedUser().checkContainsProfile(ProfileType.MANAGEMENT);
	}

	private Boolean isUserReporter(Occurrence occurrence) {
		return getAuthenticatedUser() == occurrence.getReporter().getUser();
	}

	private User getAuthenticatedUser() {
		return userContext.getCurrentUserOrThrow();
	}

	private Employee getClosureResponsible() {
		return employeeRepository.findByUser(getAuthenticatedUser())
				.orElseThrow(() -> new ObjectNotFoundException(String.format(
						"No employee associated with the user ID %s was found", getAuthenticatedUser().getID())));
	}

}
