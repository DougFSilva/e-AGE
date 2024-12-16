package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.dto.request.UpdateTechnologicalAreaRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaOperationException;
import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaValidationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TechnologicalAreaUpdater {

	private final TechnologicalAreaRepository repository;
	private final TechnologicalAreaValidator validator;
	private final StandardLogger logger;

	public TechnologicalArea update(UpdateTechnologicalAreaRequest request) {
		try {
			TechnologicalArea area = repository.findByIdOrThrow(request.getID());
			TechnologicalArea updatedArea = updateTechnologicalAreaData(area, request);
			TechnologicalArea savedArea = repository.save(updatedArea);
			logger.info(String.format("Updated Technological Area ID %s - %s", savedArea.getID(), savedArea.getTitle()));
			return savedArea;
		} catch (ObjectNotFoundException | TechnologicalAreaValidationException e) {
			String message = String.format("Error while updating technological area %s : %s", request.getTitle(), e.getMessage());
			logger.warn(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when updating technological area %s : %s", request.getTitle(), e.getMessage());
			logger.error(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		}
	}

	private TechnologicalArea updateTechnologicalAreaData(TechnologicalArea area, UpdateTechnologicalAreaRequest request) {
		if (request.getTitle() != null && !request.getTitle().isBlank() && !request.getTitle().equalsIgnoreCase(area.getTitle())) {
			validator.uniqueTitle(request.getTitle());
			area.setTitle(request.getTitle());
		}
		if (request.getDescription() != null && !request.getDescription().isBlank()) {
			area.setDescription(request.getDescription());
		}
		return area;
	}
}
