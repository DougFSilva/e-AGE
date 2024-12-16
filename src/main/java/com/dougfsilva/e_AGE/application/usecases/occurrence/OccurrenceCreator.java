	package com.dougfsilva.e_AGE.application.usecases.occurrence;

import java.time.LocalDateTime;

import com.dougfsilva.e_AGE.application.dto.request.CreateOccurrenceRequest;
import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceCreator {

	private final OccurrenceRepository repository;
	private final EmployeeRepository employeeRepository;
	private final StudentRepository studentRepository;
	private final ClazzRepository clazzRepository;
	private final OccurrenceNotificator occurrenceNotificator;
	private final StandardLogger logger;

	public OccurrenceResponse create(CreateOccurrenceRequest request) {
		try {
			Employee reporter = employeeRepository.findByIdOrThrow(request.getReporterID());
			Student student = studentRepository.findByIdOrThrow(request.getStudentID());
			Clazz clazz = clazzRepository.findByIdOrThrow(request.getClazzID());
			Occurrence occurrence = new Occurrence(
					LocalDateTime.now(), 
					reporter, 
					student, 
					clazz,
					request.getCurricularUnit(), 
					request.getOccurrenceType(), 
					request.getRestricted(),
					request.getForwarding(), 
					request.getDescription());
			Occurrence savedOccurrence = repository.save(occurrence);
			sendNotifications(request, savedOccurrence);
			logger.info(String.format("Created occurrence ID %s to student %s", savedOccurrence.getID(), savedOccurrence.getStudent().getName()));
			return OccurrenceResponse.fromOccurrence(savedOccurrence);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while creating occurrence to student ID %s : %s", request.getStudentID(), e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating occurrence to student ID %s : %s", request.getStudentID(), e.getMessage());
			logger.error(message, e);
			throw new OccurrenceOperationException(message, e);
		}
	}
	
	private void sendNotifications(CreateOccurrenceRequest request, Occurrence occurrence) {
		if (request.getSendEmailNotification()) {
			occurrenceNotificator.notifyByEmail(occurrence);
		}
		if (request.getSendPhoneNotification()) {
			occurrenceNotificator.notifyByPhone(occurrence);
		}
	}
	
}
