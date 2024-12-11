package com.dougfsilva.e_AGE.application.usecases.employee;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
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
			User createdUser = createDefaultUser(employee);
			employee.setUser(createdUser);
			Employee updatedEmployee = employeeRepository.save(employee);
			logger.info(String.format("Created user to employee ID %s, %s", updatedEmployee.getID(), updatedEmployee.getName()));
			return EmployeeResponse.fromEmployee(updatedEmployee);
		} catch (ObjectNotFoundException | UserOperationException e) {
			String message = String.format("Error while creating user to employee ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new EmployeeOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating user to employee ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new EmployeeOperationException(message, e);
		}
	}
	
	private User createDefaultUser(Employee employee) {
		if(employee.getUser() != null) {
			throw new UserOperationException(String.format("Employee %s already has a user", employee.getName()));
		}
		String username = employee.getEmail().getAddress();
		Password password = new Password("Ps" + employee.getRg() + "@", encoder);
		User user = new User(username, password, Arrays.asList(new Profile(ProfileType.EMPLOYEE)), false);
		return repository.save(user);
	}
	
}
