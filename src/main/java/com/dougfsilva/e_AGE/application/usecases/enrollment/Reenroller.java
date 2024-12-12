package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.application.dto.request.ReenrollRequest;
import com.dougfsilva.e_AGE.application.dto.response.EnrollmentResponse;
import com.dougfsilva.e_AGE.application.usecases.clazz.ClazzFinder;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.exception.CertificateValidationException;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentOperationException;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Reenroller {

	private final EnrollmentRepository repository;
	private final EnrollmentFinder enrollmentFinder;
	private final ClazzFinder clazzFinder;
	private final EnrollmentValidator validator;
	private final StandardLogger logger;

	public EnrollmentResponse reenroll(ReenrollRequest request) {
		try {
			Clazz clazz = clazzFinder.findByID(request.getClazzID());
			validator.clazzIsNotClosed(clazz);
			Enrollment currentEnrollment = enrollmentFinder.findByID(request.getEnrollmentID());
			checkEnrollmentStatus(currentEnrollment);
			validator.studentNotEnrolledInClazz(currentEnrollment.getStudent(), clazz);
			Enrollment newEnrollment = new Enrollment(currentEnrollment.getRegistration(),
					currentEnrollment.getStudent(), clazz, request.getDate(), EnrollmentStatus.ENROLLED);
			Enrollment createdEnrollment = repository.save(newEnrollment);
			updateCurrentEnrollment(currentEnrollment);
			logger.info(String.format("Stundent %s reenrolled to clazz %s", createdEnrollment.getStudent().getName(),
					clazz.getCode()));
			return EnrollmentResponse.fromEnrollment(createdEnrollment);
		} catch (ObjectNotFoundException | EnrollmentValidationException e) {
			String message = String.format("Error while reactivating enrollment ID %s : %s", request.getEnrollmentID(), e.getMessage());
			logger.warn(message, e);
			throw new EnrollmentOperationException(message, e);
		}  catch (Exception e) {
			String message = String.format("Unexpected error when reactivating enrollment ID %s : %s", request.getEnrollmentID(), e.getMessage());
			logger.error(message, e);
			throw new EnrollmentOperationException(message, e);
		}
	}

	private void updateCurrentEnrollment(Enrollment enrollment) {
		enrollment.setStatus(EnrollmentStatus.COMPLETED);
		repository.save(enrollment);
	}
	
	private void checkEnrollmentStatus(Enrollment enrollment) {
		if (enrollment.getStatus() == EnrollmentStatus.DROPPED) {
			throw new CertificateValidationException("Cannot certify a dropout student");
		}
	}
}
