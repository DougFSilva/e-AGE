package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.dto.request.UpdateEnterpriseRequest;
import com.dougfsilva.e_AGE.application.usecases.address.UpdateAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateEnterprise {

	private final EnterpriseRepository repository;
	
	private final FindEnterprise findEnterprise;
	
	private final UpdateAddress updatedAddress;
	
	private final StandardLogger logger;
	
	public Enterprise execute(UpdateEnterpriseRequest request) {
		Enterprise enterprise = findEnterprise.findByID(request.getID());
		if(!request.getName().equalsIgnoreCase(enterprise.getName()) && request.getName() != null && !request.getName().isBlank()) {
			repository.findByName(request.getName()).ifPresent(e -> {
				throw new DataIntegrityViolationException(String.format("Enterprise with name %S already exists!", e.getName()));
			});
			enterprise.setName(request.getName());
		}
		if(request.getTIN() != null && !request.getTIN().isBlank()) {
			enterprise.setTIN(request.getTIN());
		}
		if(request.getAddress() != null) {
			Address address = updatedAddress.execute(request.getAddress());
			enterprise.setAddress(address);
		}
		Enterprise updatedEnterprise = repository.save(enterprise);
		logger.updatedObjectLog(updatedEnterprise);
		return updatedEnterprise;
	}
}
