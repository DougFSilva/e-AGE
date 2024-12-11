package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaOperationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TechnologicalAreaImageDeleter {
	
	private final TechnologicalAreaRepository repository;
	private final TechnologicalAreaFinder areaFinder;
	private final ImageStorageService  imageService;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			TechnologicalArea area = areaFinder.findByID(ID);
			deleteImage(area);
	        logger.info(String.format("Image deleted successfully for technological ID %s - %s ", area.getID(), area.getTitle()));
		} catch (ObjectNotFoundException | ImageOperationException e) {
			String message = String.format("Error while deleting technological area image ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting technological area image ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		}
	}
	
	private void deleteImage(TechnologicalArea area) {
		imageService.deleteImage(ImageType.TECHNOLOGICAL_AREA, ImageNameGenerator.byTechnologicalArea(area));
		area.setImage(null);
		repository.save(area);
	}
}
