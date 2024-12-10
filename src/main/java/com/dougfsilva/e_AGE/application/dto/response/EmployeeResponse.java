package com.dougfsilva.e_AGE.application.dto.response;

import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.StaffRole;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class EmployeeResponse extends PersonResponse {

	private String registration;
	private StaffRole staffRole;
	private Boolean active;

	public EmployeeResponse(Employee employee) {
		super(
				employee.getID(), 
				employee.getUser(),
				employee.getName(),
				employee.getSex(), 
				employee.getRg(), 
				employee.getPhone(),
				employee.getEmail().getAddress(), 
				employee.getDateOfBirth(),
				employee.getAddress());
		this.registration = employee.getRegistration();
		this.staffRole = employee.getStaffRole();
		this.active = employee.getActive();
	}
	
	public static Page<EmployeeResponse> fromPage(Page<Employee> employees) {
		return new Page<EmployeeResponse>(
				employees.getContent()
				.stream()
				.map(EmployeeResponse::new)
				.collect(Collectors.toList()), 
				employees.getNumber(), 
				employees.getSize(),
				employees.getTotalElements(),
				employees.getTotalPages(), 
				employees.getHasContent(), 
				employees.getIsFirst(),
				employees.getIsLast());
	}
	
	public static EmployeeResponse fromEmployee(Employee employee) {
		return new EmployeeResponse(employee);
	}

}
