package com.dougfsilva.e_AGE.application.usecases.certificate;

import com.dougfsilva.e_AGE.application.dto.request.CreateCertificateRequest;
import com.dougfsilva.e_AGE.application.dto.response.CertificateResponse;
import com.dougfsilva.e_AGE.application.usecases.enrollment.EnrollmentFinder;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.certificate.Certificate;
import com.dougfsilva.e_AGE.domain.certificate.CertificateRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.exception.CertificateOperationException;
import com.dougfsilva.e_AGE.domain.exception.CertificateValidatorException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentCertifier {

	private final CertificateRepository repository;
	private final EnrollmentRepository enrollmentRepository;
	private final EnrollmentFinder enrollmentFinder;
	private final StandardLogger logger;
	
	public CertificateResponse certify(CreateCertificateRequest request) {
		try {
			Enrollment enrollment = enrollmentFinder.findByID(request.getEnrollmentID());
			checkEnrollmentStatus(enrollment);
			updateEnrollmentStatus(enrollment);
			Course course = enrollment.getClazz().getCourse();
			Certificate certificate = new Certificate(enrollment.getStudent(), course, request.getCertificationDate());
			Certificate createdCertificate = repository.save(certificate);
			logger.info(String.format("Student %s certified in course %s", createdCertificate.getStudent().getName(),
					createdCertificate.getCourse().getTitle()));
			return CertificateResponse.fromCertificate(createdCertificate);
		} catch (ObjectNotFoundException | CertificateValidatorException e) {
			String message = String.format("Error while certifier student with enrollment ID %s : %s", request.getEnrollmentID(), e.getMessage());
			logger.warn(message, e);
			throw new CertificateOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when certifier student with enrollment ID %s : %s", request.getEnrollmentID(), e.getMessage());
			logger.error(message, e);
			throw new CertificateOperationException(message, e);
		}

	}

	private void updateEnrollmentStatus(Enrollment enrollment) {
		enrollment.setStatus(EnrollmentStatus.COMPLETED);
		enrollmentRepository.save(enrollment);
	}

	private void checkEnrollmentStatus(Enrollment enrollment) {
		if (enrollment.getStatus() == EnrollmentStatus.DROPPED) {
			throw new CertificateValidatorException("Cannot certify a dropout student");
		}
	}
}
