package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.employee.StaffRole;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeFinder {

	private final EmployeeRepository repository;

	public EmployeeResponse findByID(String ID) {
		return EmployeeResponse.fromEmployee(repository.findByIdOrThrow(ID));
	}
	
	public EmployeeResponse findByUser(User user){
		Employee employee = repository.findByUser(user).orElseThrow(() -> new ObjectNotFoundException(
				String.format("No employee associated with the user ID %s was found", user.getID())));
		return EmployeeResponse.fromEmployee(employee);
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
