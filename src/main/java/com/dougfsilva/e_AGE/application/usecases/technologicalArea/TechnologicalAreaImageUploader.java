package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import org.springframework.web.multipart.MultipartFile;

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
public class TechnologicalAreaImageUploader {

	private final TechnologicalAreaRepository repository;
	private final ImageStorageService imageService;
	private final StandardLogger logger;

	public TechnologicalArea upload(String ID, MultipartFile image) {
		try {
			validateImage(image);
			TechnologicalArea area = repository.findByIdOrThrow(ID);
			TechnologicalArea updatedArea = uploadImage(area, image);
	        logger.info(String.format("Image uploaded successfully for technological area ID %s - %s ", area.getID(), area.getTitle()));
			return updatedArea;
		} catch (ObjectNotFoundException | IllegalArgumentException | ImageOperationException e) {
			String message = String.format("Error while uploading technological area image ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when uploading technological area image ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		}
	}
	
	private TechnologicalArea uploadImage(TechnologicalArea area, MultipartFile image) {
		String imageUrl = imageService.uploadImage(image, ImageType.TECHNOLOGICAL_AREA, ImageNameGenerator.byTechnologicalArea(area));
		area.setImage(imageUrl);
		return repository.save(area);
	}

	private void validateImage(MultipartFile image) {
		if (image == null || image.isEmpty()) {
			throw new IllegalArgumentException("Image cannot be null or Empty");
		}
	}
}
