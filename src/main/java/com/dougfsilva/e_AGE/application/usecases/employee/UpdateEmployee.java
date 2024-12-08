package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.dto.request.UpdateEmployeeRequest;
import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.address.UpdateAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;
import com.dougfsilva.e_AGE.domain.person.PersonRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateEmployee {

	private final EmployeeRepository repository;

	private final PersonRepository personRepository;

	private final UpdateAddress updateAddress;

	private final FindEmployee findEmployee;

	private final StandardLogger logger;

	public EmployeeResponse execute(UpdateEmployeeRequest request) {
		Employee employee = findEmployee.findByID(request.getID());
		updateEmployeeData(employee, request);
		Employee updatedEmployee = repository.save(employee);
		logger.info(String.format("Updated Employee ID %s - %s", employee.getID(), employee.getName()));
		return EmployeeResponse.fromEmployee(updatedEmployee);

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
			validateUniqueRg(request.getRg());
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
			validateUniqueRegistration(request.getRegistration());
			employee.setRegistration(request.getRegistration());
		}
		if (request.getStaffRole() != null) {
			employee.setStaffRole(request.getStaffRole());
		}
		if (request.getActive() != null) {
			employee.setActive(request.getActive());
		}
	}

	private void validateUniqueRg(String rg) {
		if (personRepository.existsByRg(rg)) {
			throw new DataIntegrityViolationException(String.format("Person with Rg %S already exists!", rg));
		}
	}

	private void validateUniqueRegistration(String registration) {
		if (repository.existsByRegistration(registration)) {
			throw new DataIntegrityViolationException(
					String.format("Employee with Registration %S already exists!", registration));
		}
	}
}
