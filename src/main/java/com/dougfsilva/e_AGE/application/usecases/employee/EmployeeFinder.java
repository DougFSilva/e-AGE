package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.employee.StaffRole;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeFinder {

	private final EmployeeRepository repository;

	public Employee findByID(String ID) {
		return repository.findByID(ID)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("Employee with ID %s not found", ID)));
	}

	public EmployeeResponse findByIDAsEmployeeResponse(String ID) {
		return EmployeeResponse.fromEmployee(findByID(ID));
	}

	public EmployeeResponse findByRegistration(String registration) {
		Employee employee = repository.findByRegistration(registration).orElseThrow(() -> new ObjectNotFoundException(
				String.format("Employee with Registration %s not found", registration)));
		return EmployeeResponse.fromEmployee(employee);
	}

	public Page<EmployeeResponse> findAllByStaffRole(StaffRole staffRole, PageRequest pageRequest) {
		return EmployeeResponse.fromPage(repository.findAllByStaffRole(staffRole, pageRequest));
	}

	public Page<EmployeeResponse> findAll(PageRequest pageRequest) {
		return EmployeeResponse.fromPage(repository.findAll(pageRequest));
	}

}
