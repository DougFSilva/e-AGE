package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.usecases.address.DeleteAddress;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteResponsible {

	private final ResponsibleRepository repository;
	
	private final StudentRepository studentRepository;
	
	private final FindResponsible findResponsible;
	
	private final DeleteAddress deleteAddress;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		Responsible responsible = findResponsible.findByID(ID);
		if(studentRepository.existsByResponsible(responsible)) {
			throw new DataIntegrityViolationException(
					String.format("The Responsible %S cannot be deleted because there are student(s) still associated with it!", 
							responsible.getName()));
		}
		repository.delete(responsible);
		deleteAddress.execute(responsible.getAddress().getID());
		logger.deletedObjectLog(responsible);
	}
}
