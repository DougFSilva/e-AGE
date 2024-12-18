package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.application.usecases.address.AddressDeleter;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.EnterpriseOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OperationNotAllowedException;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnterpriseDeleter {

	private final EnterpriseRepository repository;
	private final StudentRepository studentRepository;
	private final AddressDeleter addressDeleter;
	
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			Enterprise enterprise = repository.findByIdOrThrow(ID);
			ensureNoStudentsRegisteredInTheEnterprise(enterprise);
			repository.delete(enterprise);
			addressDeleter.deleteByID(enterprise.getAddress().getID());
			logger.info(String.format("Delete enterprise ID %s, %s", enterprise.getID(), enterprise.getName()));
		} catch (ObjectNotFoundException | OperationNotAllowedException e) {
			String message = String.format("Error while deleting enterprise ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new EnterpriseOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting enterprise ID %s", ID , e.getMessage());
			logger.error(message, e);
			throw new EnterpriseOperationException(message, e);
		}
	}
	
	private void ensureNoStudentsRegisteredInTheEnterprise(Enterprise enterprise) {
		if(studentRepository.existsByEnterprise(enterprise)) {
			throw new OperationNotAllowedException(
					String.format("The Enterprise %s cannot be deleted because there are students still associated with it!", 
							enterprise.getName()));
		}
	}
	
}
