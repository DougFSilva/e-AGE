package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.usecases.address.DeleteAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteEnterprise {

	private final EnterpriseRepository repository;
	
	private final FindEnterprise findEnterprise;
	
	private final DeleteAddress deleteAddress;
	
	private final EnterpriseValidator validator;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		try {
			Enterprise enterprise = findEnterprise.findByID(ID);
			validator.validateNoStudentsRegisteredWithTheEnterprise(enterprise);
			repository.delete(enterprise);
			deleteAddress.execute(enterprise.getAddress().getID());
			logger.info(String.format("Delete Enterprise ID %s - %s", enterprise.getID(), enterprise.getName()));
		} catch (Exception e) {
			logger.error("Unexpected error when deleting enterprise: " + e.getMessage());
			throw new EnterpriseOperationException("Error while delete enterprise", e);
		}
	}
	
}
