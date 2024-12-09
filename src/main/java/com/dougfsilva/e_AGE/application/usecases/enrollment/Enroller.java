package com.dougfsilva.e_AGE.application.usecases.enrollment;

import com.dougfsilva.e_AGE.application.dto.request.EnrollRequest;
import com.dougfsilva.e_AGE.application.dto.response.EnrollmentResponse;
import com.dougfsilva.e_AGE.application.usecases.clazz.ClazzFinder;
import com.dougfsilva.e_AGE.application.usecases.student.StudentFinder;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentOperationException;
import com.dougfsilva.e_AGE.domain.exception.EnrollmentValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.student.Student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Enroller {

	private final EnrollmentRepository repository;
	private final StudentFinder studentFinder;
	private final ClazzFinder clazzFinder;
	private final EnrollmentValidator validator;
	private final StandardLogger logger;

	public EnrollmentResponse enroll(EnrollRequest request) {
		try {
			validator.uniqueRegistration(request.getRegistration());
			Student student = studentFinder.findByID(request.getStudentID());
			Clazz clazz = clazzFinder.findByID(request.getClazzID());
			validator.clazzIsNotClosed(clazz);
			validator.studentNotEnrolledInClazz(student, clazz);
			Enrollment enrollment = new Enrollment(request.getRegistration(), student, clazz, request.getDate(), EnrollmentStatus.ENROLLED);
			Enrollment createEnrollment = repository.save(enrollment);
			logger.info(String.format("Student %s enrolled in class %s", student.getName(), clazz.getCode()));
			return EnrollmentResponse.fromEnrollment(createEnrollment);
		} catch (ObjectNotFoundException | EnrollmentValidationException e) {
			String message = String.format("Error while creating enrollment to student ID %s : %s", request.getStudentID(), e.getMessage());
			logger.warn(message, e);
			throw new EnrollmentOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating enrollment to student ID %s : %s", request.getStudentID(), e.getMessage());
			logger.error(message, e);
			throw new EnrollmentOperationException(message, e);
		}
	}
}
