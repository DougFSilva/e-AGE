package com.dougfsilva.e_AGE.domain.employee;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface EmployeeRepository {

	Employee save(Employee employee);
	void delete(Employee employee);
	Optional<Employee> findByID(String ID);
	default Employee findByIdOrThrow(String ID) {
	    return findByID(ID)
	        .orElseThrow(() -> new ObjectNotFoundException("Employee not found for ID: " + ID));
	}
	Optional<Employee> findByUser(User user);
	Optional<Employee> findByRegistration(String registration);
	Page<Employee> findAllByStaffRole(StaffRole staffRole, PageRequest pageRequest);
	Page<Employee> findAll(PageRequest pageRequest);
	Boolean existsByRegistration(String registration);
	
}
