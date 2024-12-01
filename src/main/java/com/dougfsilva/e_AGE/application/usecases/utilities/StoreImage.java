package com.dougfsilva.e_AGE.application.usecases.utilities;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.domain.utilities.exception.ImageStorageException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StoreImage {
	
	private final StandardLogger logger;
	
	private final ImageStorageService imageStorageService;

	public String execute(MultipartFile image, ImageType imageType, String name) {
		String imageUrl;
		try {
			if (image == null || image.isEmpty()) {
				imageUrl = imageStorageService.getDefaultImage(imageType);
			} else {
				imageUrl = imageStorageService.storeImage(image, imageType, name);
			}
		} catch (Exception e) {
			logger.imageStoreErrorLog(image);
			throw new ImageStorageException(e.getMessage());
		}
		return imageUrl;
	}
}
