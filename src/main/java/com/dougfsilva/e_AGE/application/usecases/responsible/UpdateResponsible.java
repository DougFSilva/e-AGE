package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.dto.request.UpdateResponsibleRequest;
import com.dougfsilva.e_AGE.application.dto.response.ResponsibleResponse;
import com.dougfsilva.e_AGE.application.usecases.address.UpdateAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateResponsible {

	private final ResponsibleRepository repository;

	private final FindResponsible findResponsible;

	private final UpdateAddress updateAddress;

	private final StandardLogger logger;

	public ResponsibleResponse execute(UpdateResponsibleRequest request) {
		Responsible responsible = findResponsible.findByID(request.getID());
		if (!request.getRg().equalsIgnoreCase(responsible.getRg()) && request.getRg() != null && !request.getRg().isBlank()) {
			repository.findByRg(request.getRg()).ifPresent(r -> {
				throw new DataIntegrityViolationException(
						String.format("Responsible with rg %S already exists", r.getRg()));
			});
			responsible.setRg(request.getRg());
		}
		if (request.getName() != null && !request.getName().isBlank()) {
			responsible.setName(request.getName());
		}
		if (request.getSex() != null ) {
			responsible.setSex(request.getSex());
		}
		if (request.getPhone() != null && request.getPhone().isBlank()) {
			responsible.setPhone(request.getPhone());
		}
		if (request.getEmail() != null) {
			responsible.setEmail(request.getEmail());
		}
		if (request.getDateOfBirth() != null) {
			responsible.setDateOfBirth(request.getDateOfBirth());
		}
		Address updatedAddress = updateAddress.execute(request.getAddress());
		responsible.setAddress(updatedAddress);
		Responsible updatedResponsible = repository.save(responsible);
		logger.updatedObjectLog(updatedResponsible);
		return ResponsibleResponse.fromResponsible(updatedResponsible);
	}
}
