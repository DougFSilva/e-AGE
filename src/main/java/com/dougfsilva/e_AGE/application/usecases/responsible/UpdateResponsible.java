package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.dto.request.ResponsibleDataRequest;
import com.dougfsilva.e_AGE.application.dto.response.ResponsibleResponse;
import com.dougfsilva.e_AGE.application.usecases.Address.UpdatedAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateResponsible {

	private final ResponsibleRepository repository;
	
	private final UpdatedAddress updatedAddress;
	
	private final FindResponsible findResponsible;
	
	private final StandardLogger logger;
	
	public ResponsibleResponse execute(String ID, ResponsibleDataRequest request) {
		Responsible responsible = findResponsible.findByID(ID);
		Address address = updatedAddress.execute(responsible.getAddress().getID(), request.addressDataRequest());
		responsible.setAddress(address);
		responsible.setName(request.name());
		responsible.setRg(request.rg());
		responsible.setPhone(request.phone());
		responsible.setEmail(new Email(request.email()));
		responsible.setDateOfBirth(request.dateOfBirth());
		Responsible updatedResponsible = repository.save(responsible);
		logger.updatedObjectLog(updatedResponsible);
		return ResponsibleResponse.fromResponsible(updatedResponsible);
	}
}
