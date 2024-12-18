package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.certificate.CertificateRepository;
import com.dougfsilva.e_AGE.domain.dropout.DropoutRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.StudentOperationException;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentDeleter {

	private final StudentRepository repository;
	private final UserRepository userRepository;
	private final DropoutRepository dropoutRepository;
	private final CertificateRepository certificateRepository;
	private final EnrollmentRepository enrollmentRepository;
	private final OccurrenceRepository occurrenceRepository;
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			Student student = repository.findByIdOrThrow(ID);
			if (student.getUser() != null) {
				userRepository.delete(student.getUser());
			}
			dropoutRepository.deleleAllBydeleteAllByStudent(student);
			certificateRepository.deleteAllByStudent(student);
			enrollmentRepository.deleteAllByStudent(student);
			occurrenceRepository.deleteAllByStudent(student);
			repository.delete(student);
			logger.info(String.format("Deleted Student ID %s, %s", student.getID(), student.getName()));
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while deleting student ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new StudentOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting student ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new StudentOperationException(message, e);
		}
	}
}
