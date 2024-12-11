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

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzCloser {

	private final ClazzRepository repository;
	private final EnrollmentRepository enrollmentRepository;
	private final ClazzFinder clazzFinder;
	private final StandardLogger logger;

	public ClazzResponse closeByID(String ID, LocalDate date) {
		try {
			Clazz clazz = clazzFinder.findByID(ID);
			List<Enrollment> enrollments = enrollmentRepository.findAllByClazz(clazz);
			checkForEnrolledStudents(enrollments);
			Clazz closedClazz = closeClazz(clazz, date);
			logger.info(String.format("Closed Class ID %s - %s", closedClazz.getID(), closedClazz.getCode()));
			return ClazzResponse.fromClazz(closedClazz);
		} catch (ClazzOperationException e) {
			logger.warn("Error closing class: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("Unexpected error when closing class: " + e.getMessage());
			throw new ClazzOperationException("Error while close class", e);
		}
	}

	private void checkForEnrolledStudents(List<Enrollment> enrollments) {
		if (enrollments.stream().anyMatch(enrollment -> enrollment.getStatus() == EnrollmentStatus.ENROLLED)) {
			throw new ClazzOperationException("Class cannot be closed because there are enrolled students!");
		}
	}

	private Clazz closeClazz(Clazz clazz, LocalDate date) {
		clazz.setIsClosed(true);
		clazz.setClosingDate(date);
		return repository.save(clazz);
	}

}
