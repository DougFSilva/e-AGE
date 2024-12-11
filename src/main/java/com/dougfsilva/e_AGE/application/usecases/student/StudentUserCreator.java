package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.usecases.user.UserCreator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.StudentOperationException;
import com.dougfsilva.e_AGE.domain.exception.UserOperationException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.user.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentUserCreator {

	private final StudentRepository repository;
	private final UserCreator userCreator;
	private final StudentFinder studentFinder;
	private final StandardLogger logger;
	
	public StudentResponse createByID(String ID) {
		
		try {
			Student student = studentFinder.findByID(ID);
			User user = userCreator.create(student);
			student.setUser(user);
			return StudentResponse.fromStudent(repository.save(student));
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while creating user to student %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new StudentOperationException(message, e);
		} catch(UserOperationException e) {
			logger.warn(e.getMessage(), e);
			throw new StudentOperationException(e.getMessage(), e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating user to student %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new StudentOperationException(message, e);
		}
	}
}
