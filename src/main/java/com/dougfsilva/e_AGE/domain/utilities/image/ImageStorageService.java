package com.dougfsilva.e_AGE.domain.utilities.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

	String storeImage(MultipartFile image, ImageType imageType, String name);
	
	String getDefaultImage(ImageType imageType);
	
	void deleteImage(String imageUrl, ImageType imageType);
	
}
