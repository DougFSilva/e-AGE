package com.dougfsilva.e_AGE.application.usecases.utilities;

import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteImage {

	private final StandardLogger logger;

	private final ImageStorageService imageStorageService;

	public boolean execute(String imageUrl, ImageType imageType) {
		try {
			if (imageUrl == null || imageUrl.isEmpty()) {
				return false;
			}
			imageStorageService.deleteImage(imageUrl, imageType);
			return true;
		} catch (Exception e) {
			logger.info(String.format("Deleted Image %S", imageUrl));
			return false;
		}
	}

}
