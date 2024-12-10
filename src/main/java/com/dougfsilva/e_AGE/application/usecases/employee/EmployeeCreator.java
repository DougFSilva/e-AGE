package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.dto.request.CreateEmployeeRequest;
import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.address.AddressCreator;
import com.dougfsilva.e_AGE.application.usecases.person.PersonValidator;
import com.dougfsilva.e_AGE.application.usecases.user.EmployeeUserCreator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;
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
			Employee createdEmployee = repository.save(employee);
			if(request.getCreateDefaultUser() != null && request.getCreateDefaultUser()) {
				employeeUserCreator.createByID(createdEmployee.getID());
			}
			logger.info(String.format("Created Employee ID %s - %s", employee.getID(), employee.getName()));
			return EmployeeResponse.fromEmployee(createdEmployee);
		} catch (Exception e) {
			logger.error("Unexpected error when creating employee: " + e.getMessage());
			throw new EmployeeOperationException("Error while create employee", e);
		}
		
	}
}
