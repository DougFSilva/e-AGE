package com.dougfsilva.e_AGE.application.usecases.occurrence;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.exception.UnauthorizedUserException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceStatus;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceFinisher {

	private final OccurrenceRepository repository;
	private final EmployeeRepository employeeRepository;
	private final OccurrenceOperationValidator validator;
	private final UserContext userContext;
	private final StandardLogger logger;

	public OccurrenceResponse finish(String ID) {
		try {
			Occurrence occurrence = repository.findByIdOrThrow(ID);
			validateUserPermission(occurrence);
			validator.ensureIsSigned(occurrence);
			occurrence.setFinishingDate(LocalDateTime.now());
			occurrence.setStatus(OccurrenceStatus.FINISHED);
			Employee employee = getResponsibleForFinishing();
			occurrence.setResponsibleForFinishing(employee);
			Occurrence savedOccurrence = repository.save(occurrence);
			logger.info(String.format("Finished occurrcence ID %s of the student %s", savedOccurrence.getID(), savedOccurrence.getStudent().getName()));
			return OccurrenceResponse.fromOccurrence(savedOccurrence);
		} catch (ObjectNotFoundException | UnauthorizedUserException | OperationNotAllowedException e) {
			String message = String.format("Error while finishing occurrence with ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when finishing occurrence with ID %s : %s", ID,
					e.getMessage());
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

	private Employee getResponsibleForFinishing() {
		return employeeRepository.findByUser(userContext.getCurrentUserOrThrow()).orElseThrow(
				() -> new OperationNotAllowedException("responsible for closing the occurrence not found!"));
	}

}
