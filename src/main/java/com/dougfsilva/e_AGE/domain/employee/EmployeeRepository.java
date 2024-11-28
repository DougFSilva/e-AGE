package com.dougfsilva.e_AGE.domain.employee;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface EmployeeRepository {

	Employee save(Employee employee);
	
	void delete(Employee employee);
	
	Optional<Employee> findByID(String ID);
	
	Page<Employee> findAllByStaffRole(StaffRole staffRole, PageRequest pageRequest);
	
	Page<Employee> findAll(PageRequest pageRequest);
}
