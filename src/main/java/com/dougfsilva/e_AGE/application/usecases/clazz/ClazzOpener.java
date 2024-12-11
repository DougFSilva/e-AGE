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
	private final ClazzFinder clazzFinder;
	private final StandardLogger logger;
	
	public ClazzResponse openByID(String ID) {
		try {
			Clazz clazz = clazzFinder.findByID(ID);
			Clazz openedClazz = openClazz(clazz);
			logger.info(String.format("Opened clazz ID %s, code %s", openedClazz.getID(), openedClazz.getCode()));
			return ClazzResponse.fromClazz(openedClazz);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while open class ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when opening class ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}
	
	private Clazz openClazz(Clazz clazz) {
		clazz.setClosingDate(null);
		clazz.setIsClosed(false);
		return repository.save(clazz);
	}
}
