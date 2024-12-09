package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.usecases.address.AddressDeleter;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseOperationException;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseValidationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

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
			logger.info(String.format("Delete enterprise ID %s, %s", enterprise.getID(), enterprise.getName()));
		} catch (ObjectNotFoundException | EnterpriseValidationException e) {
			String message = String.format("Error while deleting enterprise ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new EnterpriseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting enterprise ID %s", ID , e.getMessage());
			logger.error(message, e);
			throw new EnterpriseOperationException(message, e);
		}
	}
	
}
