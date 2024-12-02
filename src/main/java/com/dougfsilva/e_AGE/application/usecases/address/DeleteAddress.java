package com.dougfsilva.e_AGE.application.usecases.Address;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteAddress {

	private final AddressRepository repository;

	private final ResponsibleRepository responsibleRepository;

	private final EmployeeRepository employeeRepository;

	private final StudentRepository studentRepository;

	private final EnterpriseRepository enterpriseRepository;

	private final FindAddress findAddress;

	public void execute(String ID) {
		Address address = findAddress.findByID(ID);
		if (responsibleRepository.existsByAddress(address) || employeeRepository.existsByAddress(address)
				|| studentRepository.existsByAddress(address) || enterpriseRepository.existsByAddress(address)) {
			return;

		}
		repository.delete(address);
	}
}
