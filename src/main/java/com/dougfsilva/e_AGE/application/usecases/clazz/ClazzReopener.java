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
public class ClazzReopener {

	private final ClazzRepository repository;
	private final ClazzFinder clazzFinder;
	private final StandardLogger logger;
	
	public ClazzResponse reopenByID(String ID) {
		try {
			Clazz clazz = clazzFinder.findByID(ID);
			clazz.setClosingDate(null);
			clazz.setIsClosed(false);
			Clazz reopenClazz = repository.save(clazz);
			logger.info(String.format("Reopened clazz ID %s, code %s", reopenClazz.getID(), reopenClazz.getCode()));
			return ClazzResponse.fromClazz(reopenClazz);
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while reopen class ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new ClazzOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when reopening class ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CourseOperationException(message, e);
		}
	}
}
