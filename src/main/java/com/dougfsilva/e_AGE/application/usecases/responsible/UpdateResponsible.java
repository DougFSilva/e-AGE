package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.dto.request.ResponsibleDataRequest;
import com.dougfsilva.e_AGE.application.dto.response.ResponsibleResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateResponsible {

	private final ResponsibleRepository repository;
	
	private final AddressRepository addressRepository;
	
	private final FindResponsible findResponsible;
	
	private final StandardLogger logger;
	
	public ResponsibleResponse execute(String ID, ResponsibleDataRequest request) {
		Responsible responsible = findResponsible.findByID(ID);
		Address address = responsible.getAddress();
		address.setCountry(request.country());;
		address.setState(request.state());
		address.setPostalCode(request.postalCode());
		address.setCity(request.city());
		address.setDistrict(request.district());
		address.setStreet(request.street());
		address.setNumber(request.number());
		responsible.setAddress(address);
		responsible.setName(request.name());
		responsible.setRg(request.rg());
		responsible.setPhone(request.phone());
		responsible.setEmail(new Email(request.email()));
		responsible.setDateOfBirth(request.dateOfBirth());
		addressRepository.save(address);
		Responsible updatedResponsible = repository.save(responsible);
		logger.updatedObjectLog(updatedResponsible);
		return ResponsibleResponse.fromResponsible(updatedResponsible);
	}
}
