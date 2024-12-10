package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.usecases.address.AddressDeleter;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnterpriseDeleter {

	private final EnterpriseRepository repository;
	private final EnterpriseFinder enterpriseFinder;
	private final AddressDeleter addressDeleter;
	private final EnterpriseValidator validator;
	
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			Enterprise enterprise = enterpriseFinder.findByID(ID);
			validator.hasNoStudentsRegisteredInTheEnterprise(enterprise);
			repository.delete(enterprise);
			addressDeleter.deleteByID(enterprise.getAddress().getID());
			logger.info(String.format("Delete Enterprise ID %s - %s", enterprise.getID(), enterprise.getName()));
		} catch (Exception e) {
			logger.error("Unexpected error when deleting enterprise: " + e.getMessage());
			throw new EnterpriseOperationException("Error while delete enterprise", e);
		}
	}
	
}
