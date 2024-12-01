package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.dto.request.AddressDataRequest;
import com.dougfsilva.e_AGE.application.dto.request.ResponsibleDataRequest;
import com.dougfsilva.e_AGE.application.dto.response.ResponsibleResponse;
import com.dougfsilva.e_AGE.application.usecases.address.CreateAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateResponsible {

	private final ResponsibleRepository repository;
	
	private final CreateAddress createAddress;
	
	private final StandardLogger logger;

	public ResponsibleResponse execute(ResponsibleDataRequest request) {
		if(repository.findByRg(request.rg()).isPresent()) {
			throw new DataIntegrityViolationException(String.format("Legal guardian with Rg %S already registered in the system", request.rg()));
		}
		Address createdAddress = createAddress.execute(new AddressDataRequest(request.country(), request.state(), request.postalCode(), request.city(),
				request.district(), request.street(), request.number()));
		Responsible guardian = new Responsible(request.name(), request.rg(), request.phone(), new Email(request.email()),request.dateOfBirth(), createdAddress);
		Responsible createdGuardian = repository.save(guardian);
		logger.createdObjectLog(createdGuardian);
		return ResponsibleResponse.fromResponsible(createdGuardian);
	}

}
