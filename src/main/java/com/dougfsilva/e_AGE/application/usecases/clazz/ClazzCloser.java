package com.dougfsilva.e_AGE.application.usecases.clazz;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzCloser {

	private final ClazzRepository repository;
	private final EnrollmentRepository enrollmentRepository;
	private final StandardLogger logger;

	public ClazzResponse closeByID(String ID) {
		try {
			Clazz clazz = repository.findByIdOrThrow(ID);
			List<Enrollment> enrollments = enrollmentRepository.findAllByClazz(clazz);
			ensureIsNoEnrolledStudents(enrollments);
			clazz.setIsClosed(true);
			clazz.setClosingDate(LocalDate.now());
			Clazz savedClazz = repository.save(clazz);
			logger.info(String.format("Closed class ID %s, code %s", savedClazz.getID(), savedClazz.getCode()));
			return ClazzResponse.fromClazz(savedClazz);
		} catch (ObjectNotFoundException | OperationNotAllowedException e) {
			String message = String.format("Error while closing class ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when closing class ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new ClazzOperationException(message, e);
		}
	}

	private void ensureIsNoEnrolledStudents(List<Enrollment> enrollments) {
		if (enrollments.stream().anyMatch(enrollment -> enrollment.getStatus() == EnrollmentStatus.ENROLLED)) {
			throw new OperationNotAllowedException("Class cannot be closed because there are enrolled students!");
		}
	}

}
