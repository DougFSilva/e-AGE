package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.usecases.address.DeleteAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteEnterprise {

	private final EnterpriseRepository repository;
	
	private final StudentRepository studentRepository;
	
	private final FindEnterprise findEnterprise;
	
	private final DeleteAddress deleteAddress;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		Enterprise enterprise = findEnterprise.findByID(ID);
		if(studentRepository.existsByEnterprise(enterprise)) {
			throw new DataIntegrityViolationException(
					String.format("The Enterprise %S cannot be deleted because there are Students still associated with it!", 
							enterprise.getName()));
		}
		repository.delete(enterprise);
		deleteAddress.execute(enterprise.getAddress().getID());
		logger.info(String.format("Delete Enterprise ID %S - %S", enterprise.getID(), enterprise.getName()));
	}
}
