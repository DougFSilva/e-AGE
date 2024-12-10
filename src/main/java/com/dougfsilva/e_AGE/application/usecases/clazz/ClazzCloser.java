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
			if (enrollments.stream().anyMatch(enrollment -> enrollment.getStatus() == EnrollmentStatus.COMPLETED)) {
				throw new ClazzOperationException("It is not possible to close the class, as there are still students enrolled in it!");
			}
			clazz.setIsClosed(true);
			clazz.setClosingDate(date);
			Clazz closedClazz = repository.save(clazz);
			logger.info(String.format("Closed Class ID %s - %s", closedClazz.getID(), closedClazz.getCode()));
			return ClazzResponse.fromClazz(closedClazz);
		} catch (Exception e) {
			logger.error("Unexpected error when closing class: " + e.getMessage());
			throw new ClazzOperationException("Error while close class", e);
		}
	}

}
