package com.dougfsilva.e_AGE.application.usecases.utilities;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.domain.exception.ImageStorageException;
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
				imageUrl = imageStorageService.storeImage(image, imageType, name.trim().toLowerCase());
			}
		} catch (Exception e) {
			logger.error(String.format("Error storing image %S", name));
			throw new ImageStorageException(e.getMessage());
		}
		return imageUrl;
	}
}
