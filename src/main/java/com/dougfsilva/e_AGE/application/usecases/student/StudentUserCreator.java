package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.usecases.user.UserCreator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
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
		}
		catch (Exception e) {
			logger.error("Unexpected error when creating user to student: " + e.getMessage());
			throw new UserOperationException("Error while create user to student", e);
		}
	}
}
