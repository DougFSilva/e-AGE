package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.application.dto.request.ReenrollRequest;
import com.dougfsilva.e_AGE.application.dto.response.EnrollmentResponse;
import com.dougfsilva.e_AGE.application.usecases.clazz.ClazzFinder;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentOperationException;

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
			validator.studentNotEnrolledInClazz(currentEnrollment.getStudent(), clazz);
			Enrollment newEnrollment = new Enrollment(
					currentEnrollment.getRegistration(), 
					currentEnrollment.getStudent(), 
					clazz, 
					request.getDate(), 
					EnrollmentStatus.ENROLLED);
			Enrollment createdEnrollment = repository.save(newEnrollment);
			updateCurrentEnrollment(currentEnrollment);
			logger.info(String.format("Stundent %s reenrolled to clazz %s", createdEnrollment.getStudent().getName(), clazz.getCode()));
			return EnrollmentResponse.fromEnrollment(createdEnrollment);
		}catch (EnrollmentOperationException e) {
	        logger.error("Enrollment operation failed: " + e.getMessage());
	        throw new EnrollmentOperationException("Error while reenrolling student: " + e.getMessage(), e); 
		}
		catch (Exception e) {
			logger.error("Unexpected error when reenrolling student: " + e.getMessage());
			throw new EnrollmentOperationException("Error while reenroll student", e);
		}
	}
	
	private void updateCurrentEnrollment(Enrollment enrollment) {
		enrollment.setStatus(EnrollmentStatus.COMPLETED);
		repository.save(enrollment);
	}
}
