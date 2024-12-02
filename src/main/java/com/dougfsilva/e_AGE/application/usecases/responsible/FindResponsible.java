package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.dto.response.ResponsibleResponse;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindResponsible {

	private final ResponsibleRepository repository;

	public Responsible findByID(String ID) {
		return repository.findByID(ID).orElseThrow(() -> new ObjectNotFoundException(String.format("Responsible with ID %S not found!", ID)));
	}
	
	public ResponsibleResponse findByIDAsResponsibleResponse(String ID) {
		return ResponsibleResponse.fromResponsible(findByID(ID));
	}
	
	public Page<ResponsibleResponse> findAll(PageRequest pageRequest){
		return ResponsibleResponse.fromPage(repository.findAll(pageRequest));
	}
}
