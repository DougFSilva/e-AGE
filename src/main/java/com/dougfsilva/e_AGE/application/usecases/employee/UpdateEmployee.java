package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.dto.request.UpdateEmployeeRequest;
import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.address.UpdateAddress;
import com.dougfsilva.e_AGE.application.usecases.person.PersonValidator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateEmployee {

	private final EmployeeRepository repository;

	private final UpdateAddress updateAddress;

	private final FindEmployee findEmployee;

	private final PersonValidator personValidator;

	private final EmployeeValidator validator;

	private final StandardLogger logger;

	public EmployeeResponse execute(UpdateEmployeeRequest request) {
		try {
			Employee employee = findEmployee.findByID(request.getID());
			updateEmployeeData(employee, request);
			Employee updatedEmployee = repository.save(employee);
			logger.info(String.format("Updated Employee ID %s - %s", employee.getID(), employee.getName()));
			return EmployeeResponse.fromEmployee(updatedEmployee);
		} catch (Exception e) {
			logger.error("Unexpected error when updating employee: " + e.getMessage());
			throw new EmployeeOperationException("Error while update employee", e);
		}

	}

	private void updateEmployeeData(Employee employee, UpdateEmployeeRequest request) {
		if (request.getName() != null && !request.getName().isBlank()) {
			employee.setName(request.getName());
		}
		if (request.getSex() != null) {
			employee.setSex(request.getSex());
		}
		if (request.getRg() != null && !request.getRg().isBlank()
				&& !request.getRg().equalsIgnoreCase(employee.getRg())) {
			personValidator.validateUniqueRg(request.getRg());
			employee.setRg(request.getRg());
		}
		if (request.getPhone() != null && !request.getPhone().isBlank()) {
			employee.setPhone(request.getPhone());
		}
		if (request.getEmail() != null) {
			employee.setEmail(request.getEmail());
		}
		if (request.getDateOfBirth() != null) {
			employee.setDateOfBirth(request.getDateOfBirth());
		}
		if (request.getAddress() != null) {
			employee.setAddress(updateAddress.execute(request.getAddress()));
		}
		if (request.getRegistration() != null && !request.getRegistration().isBlank()
				&& !request.getRegistration().equalsIgnoreCase(employee.getRegistration())) {
			validator.validateUniqueRegistration(request.getRegistration());
			employee.setRegistration(request.getRegistration());
		}
		if (request.getStaffRole() != null) {
			employee.setStaffRole(request.getStaffRole());
		}
		if (request.getActive() != null) {
			employee.setActive(request.getActive());
		}
	}
}
