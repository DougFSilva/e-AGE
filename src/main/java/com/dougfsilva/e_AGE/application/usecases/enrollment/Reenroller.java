package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.application.dto.request.ReenrollRequest;
import com.dougfsilva.e_AGE.application.dto.response.EnrollmentResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.student.Student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Reenroller {

	private final EnrollmentRepository repository;
	private final ClazzRepository clazzRepository;
	private final StandardLogger logger;

	public EnrollmentResponse reenroll(ReenrollRequest request) {
		try {
			Clazz clazz = clazzRepository.findByIdOrThrow(request.getClazzID());
			ensureIsNotClosed(clazz);
			Enrollment currentEnrollment = repository.findByIdOrThrow(request.getEnrollmentID());
			ensureIsNotDropped(currentEnrollment);
			ensureIsNoStudentEnrolled(currentEnrollment.getStudent(), clazz);
			Enrollment newEnrollment = new Enrollment(currentEnrollment.getRegistration(),
					currentEnrollment.getStudent(), clazz, request.getDate(), EnrollmentStatus.ENROLLED);
			Enrollment savedEnrollment = repository.save(newEnrollment);
			currentEnrollment.setStatus(EnrollmentStatus.COMPLETED);
			repository.save(currentEnrollment);
			logger.info(String.format("Stundent %s reenrolled to clazz %s", savedEnrollment.getStudent().getName(),
					clazz.getCode()));
			return EnrollmentResponse.fromEnrollment(savedEnrollment);
		} catch (ObjectNotFoundException | OperationNotAllowedException e) {
			String message = String.format("Error while reactivating enrollment ID %s : %s", request.getEnrollmentID(), e.getMessage());
			logger.warn(message, e);
			throw new EnrollmentOperationException(message, e);
		}  catch (Exception e) {
			String message = String.format("Unexpected error when reactivating enrollment ID %s : %s", request.getEnrollmentID(), e.getMessage());
			logger.error(message, e);
			throw new EnrollmentOperationException(message, e);
		}
	}

	private void ensureIsNotDropped(Enrollment enrollment) {
		if (enrollment.getStatus() == EnrollmentStatus.DROPPED) {
			throw new OperationNotAllowedException("Cannot certify a dropout student");
		}
	}
	
	private void ensureIsNotClosed(Clazz clazz) {
		if(clazz.getIsClosed()) {
			throw new OperationNotAllowedException("It is not possible to re-enroll a student in a closed class");
		}
	}
	
	private void ensureIsNoStudentEnrolled(Student student, Clazz clazz) {
		if(repository.existsByClazzAndStudent(student, clazz)) {
			throw new OperationNotAllowedException(String.format("The student %s is already enrolled in the class %s!", student.getName(), clazz.getCode()));
		}
	}
}
