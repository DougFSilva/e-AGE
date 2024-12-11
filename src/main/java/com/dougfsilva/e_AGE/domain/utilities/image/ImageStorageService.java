package com.dougfsilva.e_AGE.domain.utilities.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

	String uploadImage(MultipartFile image, ImageType imageType, String name);
	void deleteImage(ImageType imageType, String name);
	String getDefaultImage(ImageType imageType);
	
}
