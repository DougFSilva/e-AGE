package com.dougfsilva.e_AGE.application.usecases.user;

import com.dougfsilva.e_AGE.application.dto.request.CreateUserRequest;
import com.dougfsilva.e_AGE.application.dto.response.UserResponse;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.person.Person;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.user.ProfileType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateDefaultUser {

	private final CreateUser createUser;


	public UserResponse execute(Student student) {
		String username = getUsername(student);
		String password = getPassword(student);
		return createUser.execute(new CreateUserRequest(username, password, ProfileType.STUDENT));
	}
	
	public UserResponse execute(Employee employee) {
		String username = getUsername(employee);
		String password = getPassword(employee);
		return createUser.execute(new CreateUserRequest(username, password, ProfileType.EMPLOYEE));
	}
	
	private String getUsername(Person person) {
		return person.getEmail().getAddress();
	}
	
	private String getPassword(Person person) {
		return "Ps" + person.getRg() + "@";
	}

}
