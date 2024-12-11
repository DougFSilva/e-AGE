package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.dto.request.UpdateEmployeeRequest;
import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.address.AddressUpdater;
import com.dougfsilva.e_AGE.application.usecases.person.PersonValidator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;
import com.dougfsilva.e_AGE.domain.exception.EmployeeValidatorException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeUpdater {

	private final EmployeeRepository repository;
	private final AddressUpdater addressUpdater;
	private final EmployeeFinder employeeFinder;
	private final PersonValidator personValidator;
	private final EmployeeValidator validator;
	private final StandardLogger logger;

	public EmployeeResponse update(UpdateEmployeeRequest request) {
		try {
			Employee employee = employeeFinder.findByID(request.getID());
			updateEmployeeData(employee, request);
			Employee updatedEmployee = repository.save(employee);
			logger.info(String.format("Updated Employee ID %s, %s", employee.getID(), employee.getName()));
			return EmployeeResponse.fromEmployee(updatedEmployee);
		} catch (ObjectNotFoundException | EmployeeValidatorException e) {
			String message = String.format("Error while updating employee %s : %s", request.getName(), e.getMessage());
			logger.warn(message, e);
			throw new EmployeeOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when updating employee %s : %s", request.getName(), e.getMessage());
			logger.error(message, e);
			throw new EmployeeOperationException(message, e);
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
			personValidator.uniqueRg(request.getRg());
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
			employee.setAddress(addressUpdater.update(request.getAddress()));
		}
		if (request.getRegistration() != null && !request.getRegistration().isBlank()
				&& !request.getRegistration().equalsIgnoreCase(employee.getRegistration())) {
			validator.uniqueRegistration(request.getRegistration());
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
