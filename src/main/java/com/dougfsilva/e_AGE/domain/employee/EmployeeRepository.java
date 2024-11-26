package com.dougfsilva.e_AGE.domain.employee;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface EmployeeRepository {

	Employee create(Employee employee);
	
	void delete(Employee employee);
	
	Employee update(Employee employee);
	
	Optional<Employee> findByID(String ID);
	
	List<Employee> findAllByStaffRole(StaffRole staffRole, PageRequest pageRequest);
	
	List<Employee> findAll(PageRequest pageRequest);
}
