package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.user.UserCreator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.UserOperationException;
import com.dougfsilva.e_AGE.domain.user.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeUserCreator {

	private final EmployeeRepository repository;
	private final UserCreator userCreator;
	private final StandardLogger logger;
	
	public EmployeeResponse createByID(String ID) {
		
		try {
			Employee employee = repository.findByIdOrThrow(ID);
			User user = userCreator.create(employee);
			employee.setUser(user);
			return EmployeeResponse.fromEmployee(repository.save(employee));
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while creating user to employee %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new EmployeeOperationException(message, e);
		} catch(UserOperationException e) {
			logger.warn(e.getMessage(), e);
			throw new EmployeeOperationException(e.getMessage(), e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating user to employee %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new EmployeeOperationException(message, e);
		}
	}
	
}
