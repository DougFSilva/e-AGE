package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.exception.CourseOperationException;

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
			logger.info(String.format("Reopened clazz ID %s - %s", reopenClazz.getID(), reopenClazz.getCode()));
			return ClazzResponse.fromClazz(reopenClazz);
		} catch (Exception e) {
			logger.error("Unexpected error when reopening class: " + e.getMessage());
			throw new CourseOperationException("Error while reopen class", e);
		}
	}
}
