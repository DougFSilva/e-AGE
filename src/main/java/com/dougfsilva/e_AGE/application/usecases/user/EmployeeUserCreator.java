package com.dougfsilva.e_AGE.application.usecases.user;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.employee.EmployeeFinder;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.UserOperationException;
import com.dougfsilva.e_AGE.domain.user.Password;
import com.dougfsilva.e_AGE.domain.user.PasswordEncoder;
import com.dougfsilva.e_AGE.domain.user.Profile;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeUserCreator {

	private final UserRepository repository;
	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder encoder;
	private final EmployeeFinder employeeFinder;
	private final StandardLogger logger;
	
	public EmployeeResponse createByID(String ID) {
		
		try {
			Employee employee = employeeFinder.findByID(ID);
			if(employee.getUser() != null) {
				throw new UserOperationException(String.format("Employee %s already has a user", employee.getName()));
			}
			String username = employee.getEmail().getAddress();
			Password password = new Password("Ps" + employee.getRg() + "@", encoder);
			User user = new User(username, password, Arrays.asList(new Profile(ProfileType.EMPLOYEE)), false);
			User createdUser = repository.save(user);
			employee.setUser(createdUser);
			employeeRepository.save(employee);
			return EmployeeResponse.fromEmployee(employee);
		} catch (Exception e) {
			logger.error("Unexpected error when creating user to employee: " + e.getMessage());
			throw new UserOperationException("Error while create user to employee", e);
		}
	}
}
