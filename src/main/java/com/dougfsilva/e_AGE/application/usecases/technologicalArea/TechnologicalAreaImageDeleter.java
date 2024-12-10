package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
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

	public TechnologicalArea deleteByID(String ID) {
		try {
			TechnologicalArea area = areaFinder.findByID(ID);
			imageService.deleteImage(ImageType.TECHNOLOGICAL_AREA, ImageNameGenerator.byTechnologicalArea(area));
			area.setImage(null);
			repository.save(area);
			return area;
		} catch (Exception e) {
			logger.error("Unexpected error when deleting technological area image: " + e.getMessage());
			throw new ImageOperationException("Error while delete technological area image", e);
		}

	}
}
