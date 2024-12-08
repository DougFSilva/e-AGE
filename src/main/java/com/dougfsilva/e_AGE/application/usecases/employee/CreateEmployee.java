package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.dto.request.CreateEmployeeRequest;
import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.address.CreateAddress;
import com.dougfsilva.e_AGE.application.usecases.person.PersonValidator;
import com.dougfsilva.e_AGE.application.usecases.user.CreateDefaultUser;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateEmployee {

	private final EmployeeRepository repository;

	private final CreateDefaultUser createDefaultUser;

	private final CreateAddress createAddress;
	
	private final PersonValidator personValidator;
	
	private final EmployeeValidator validator;

	private final StandardLogger logger;

	public EmployeeResponse execute(CreateEmployeeRequest request) {
		try {
			personValidator.validateUniqueRg(request.getRg());
			validator.validateUniqueRegistration(request.getRegistration());
			Address address = createAddress.execute(request.getAddress());
			Employee employee = new Employee(
					request.getName(), 
					request.getSex(), 
					request.getRg(), 
					request.getPhone(), 
					request.getEmail(), 
					request.getDateOfBirth(),
					address, 
					request.getRegistration(),
					request.getStaffRole(),
					true);
			Employee createdEmployee = repository.save(employee);
			if(request.getCreateDefaultUser() != null && request.getCreateDefaultUser()) {
				createDefaultUser.execute(createdEmployee);
			}
			logger.info(String.format("Created Employee ID %s - %s", employee.getID(), employee.getName()));
			return EmployeeResponse.fromEmployee(createdEmployee);
		} catch (Exception e) {
			logger.error("Unexpected error when creating employee: " + e.getMessage());
			throw new EmployeeOperationException("Error while create employee", e);
		}
		
	}
}
