package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.dropout.DropoutRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteClazz {

	private final ClazzRepository repository;
	
	private final EnrollmentRepository enrollmentRepository;
	
	private final DropoutRepository dropoutRepository;
	
	private final FindClazz findClazz;

	private final StandardLogger logger;

	public void execute(String ID) {
		Clazz clazz = findClazz.findByID(ID);
		if(enrollmentRepository.existsByClazz(clazz) || dropoutRepository.existsByClazz(clazz)) {
			throw new DataIntegrityViolationException(
					String.format("The Class %s cannot be deleted because there are Students still associated with it!", 
							clazz.getCode()));
		}
		repository.delete(clazz);
		logger.info(String.format("Delete Class ID %s - %s", clazz.getID(), clazz.getCode()));
	}

}
