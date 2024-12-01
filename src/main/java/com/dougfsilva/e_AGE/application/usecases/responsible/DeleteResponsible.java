package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteResponsible {

	private final ResponsibleRepository repository;
	
	private final FindResponsible findResponsible;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		Responsible responsible = findResponsible.findByID(ID);
		repository.delete(responsible);
		logger.deletedObjectLog(responsible);
	}
}
