package com.dougfsilva.e_AGE.application.usecases.occurrence;

import com.dougfsilva.e_AGE.application.dto.request.CreateOccurrenceRequest;
import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
import com.dougfsilva.e_AGE.application.usecases.clazz.ClazzFinder;
import com.dougfsilva.e_AGE.application.usecases.employee.EmployeeFinder;
import com.dougfsilva.e_AGE.application.usecases.student.StudentFinder;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.student.Student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OcurrenceCreator {

	private final OccurrenceRepository repository;
	private final EmployeeFinder employeeFinder;
	private final StudentFinder studentFinder;
	private final ClazzFinder clazzFinder;
	private final StandardLogger logger;

	public OccurrenceResponse create(CreateOccurrenceRequest request) {
		Employee reporter = employeeFinder.findByID(request.getReporterID());
		try {
			Student student = studentFinder.findByID(request.getStudantID());
			Clazz clazz = clazzFinder.findByID(request.getClazzID());
			Occurrence occurrence = new Occurrence(request.getOpeningDate(), reporter, student, clazz,
					request.getCurricularUnit(), request.getOccurrenceType(), request.getRestricted(),
					request.getForwarding(), request.getDescription());
			Occurrence createdOccurrence = repository.save(occurrence);
			logger.info(String.format("Created occurrence ID %s to student %s",createdOccurrence.getID(), createdOccurrence.getStudant().getName()));
			return OccurrenceResponse.fromOccurrence(createdOccurrence);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while creating occurrence to student ID %s : %s", request.getStudantID(), e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating occurrence to student ID %s : %s", request.getStudantID(), e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}
}
