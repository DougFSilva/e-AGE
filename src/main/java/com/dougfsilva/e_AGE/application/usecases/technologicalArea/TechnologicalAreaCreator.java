package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.dto.request.CreateTechnologicalAreaRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaOperationException;
import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaValidationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TechnologicalAreaCreator {

	private final TechnologicalAreaRepository repository;
	private final ImageStorageService imageService;
	private final TechnologicalAreaValidator validator;
	private final StandardLogger logger;

	public TechnologicalArea create(CreateTechnologicalAreaRequest request) {

		try {
			validator.uniqueTitle(request.getTitle());
			TechnologicalArea area = new TechnologicalArea(
					request.getTitle(), 
					request.getDescription(), 
					imageService.getDefaultImage(ImageType.TECHNOLOGICAL_AREA));
			TechnologicalArea savedArea = repository.save(area);
			logger.info(String.format("Create Technological Area ID %s - %s", savedArea.getID(), savedArea.getTitle()));
			return savedArea;
		} catch (TechnologicalAreaValidationException e) {
			String message = String.format("Error while creating technological area %s : %s", request.getTitle(), e.getMessage());
			logger.warn(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating technological area %s : %s", request.getTitle(), e.getMessage());
			logger.error(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		}

	}
}
