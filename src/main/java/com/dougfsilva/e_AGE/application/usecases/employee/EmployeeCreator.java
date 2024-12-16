package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.dto.request.CreateEmployeeRequest;
import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.address.AddressCreator;
import com.dougfsilva.e_AGE.application.usecases.person.PersonValidator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;
import com.dougfsilva.e_AGE.domain.exception.EmployeeValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.PersonValidationException;
import com.dougfsilva.e_AGE.domain.exception.UserOperationException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeCreator {

	private final EmployeeRepository repository;
	private final AddressCreator addressCreator;
	private final PersonValidator personValidator;
	private final ImageStorageService imageStorageService;
	private final EmployeeUserCreator employeeUserCreator;
	private final EmployeeValidator validator;
	private final StandardLogger logger;

	public EmployeeResponse create(CreateEmployeeRequest request) {
		try {
			personValidator.uniqueRg(request.getRg());
			validator.uniqueRegistration(request.getRegistration());
			Address address = addressCreator.create(request.getAddress());
			Employee employee = new Employee(
					request.getName(), 
					request.getSex(), 
					request.getRg(), 
					request.getPhone(), 
					request.getEmail(), 
					request.getDateOfBirth(),
					address,
					imageStorageService.getDefaultImage(ImageType.EMPLOYEE),
					request.getRegistration(),
					request.getStaffRole(),
					true);
			Employee savedEmployee = repository.save(employee);
			createUser(request, savedEmployee);
			logger.info(String.format("Created employee ID %s, %s", employee.getID(), employee.getName()));
			return EmployeeResponse.fromEmployee(savedEmployee);
		} catch (ObjectNotFoundException | EmployeeValidationException | PersonValidationException | UserOperationException e) {
			String message = String.format("Error while creating employee %s : %s", request.getName(), e.getMessage());
			logger.warn(message, e);
			throw new EmployeeOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating employee %s : %s", request.getName(), e.getMessage());
			logger.error(message, e);
			throw new EmployeeOperationException(message, e);
		}
	}
	
	private void createUser(CreateEmployeeRequest request, Employee employee) {
		if(request.getCreateDefaultUser()) {
			employeeUserCreator.createByID(employee.getID());
		}
	}
}
