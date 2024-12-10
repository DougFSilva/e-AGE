package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TechnologicalAreaImageUploader {

	private final TechnologicalAreaRepository repository;
	private final TechnologicalAreaFinder areaFinder;
	private final ImageStorageService imageService;
	private final StandardLogger logger;

	public TechnologicalArea upload(String ID, MultipartFile image) {
		try {
			if (image == null || image.isEmpty()) {
				throw new IllegalArgumentException("Image cannot be null or Empty");
			}
			TechnologicalArea area = areaFinder.findByID(ID);
			String imageUrl = imageService.uploadImage(image, ImageType.TECHNOLOGICAL_AREA,
					ImageNameGenerator.byTechnologicalArea(area));
			area.setImage(imageUrl);
			area = repository.save(area);
			return area;
		} catch (Exception e) {
			logger.error("Unexpected error when uploading technological area image: " + e.getMessage());
			throw new ImageOperationException("Error while upload technological area image", e);
		}

	}
}
