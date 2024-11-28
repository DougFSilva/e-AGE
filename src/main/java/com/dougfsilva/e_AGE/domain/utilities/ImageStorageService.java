package com.dougfsilva.e_AGE.domain.utilities;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

	String storeImage(MultipartFile image);
	
}
