package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.exception.ClazzOperationException;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzOpener {

	private final ClazzRepository repository;
	private final StandardLogger logger;
	
	public ClazzResponse openByID(String ID) {
		try {
			Clazz clazz = repository.findByIdOrThrow(ID);
			clazz.setClosingDate(null);
			clazz.setIsClosed(false);
			Clazz savedClazz = repository.save(clazz);
			logger.info(String.format("Opened clazz ID %s, code %s", savedClazz.getID(), savedClazz.getCode()));
			return ClazzResponse.fromClazz(savedClazz);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while opening class ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when opening class ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}
	
}
