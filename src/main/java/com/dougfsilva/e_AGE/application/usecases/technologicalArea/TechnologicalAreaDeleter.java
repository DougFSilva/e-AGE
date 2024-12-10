package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaOperationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TechnologicalAreaDeleter {

	private final TechnologicalAreaRepository repository;
	private final TechnologicalAreaFinder areaFinder;
	private final ImageStorageService imageService ;
	private final TechnologicalAreaValidator validator;
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			TechnologicalArea technologicalArea = areaFinder.findByID(ID);
			validator.hasNoCoursesInTechnologicalArea(technologicalArea);
			repository.delete(technologicalArea);
			imageService.deleteImage(ImageType.TECHNOLOGICAL_AREA, ImageNameGenerator.byTechnologicalArea(technologicalArea));
			logger.info(String.format("Deleted Technological Area ID %s - %s", technologicalArea.getID(), technologicalArea.getTitle()));
		} catch (Exception e) {
			logger.error("Unexpected error when deleting technological area: " + e.getMessage());
			throw new TechnologicalAreaOperationException("Error while delete technological area address", e);
		}
		
	}
}
