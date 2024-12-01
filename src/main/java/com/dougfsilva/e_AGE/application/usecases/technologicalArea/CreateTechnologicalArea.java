package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.dto.request.TechnologicalAreaDataRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTechnologicalArea {

	private final TechnologicalAreaRepository repository;
	
	private final StoreImage storeImage;

	private final StandardLogger logger;

	public TechnologicalArea execute(TechnologicalAreaDataRequest request) {
		String imageUrl = storeImage.execute(request.image(), ImageType.TECHNOLOGICAL_AREA, request.tilte());
		TechnologicalArea technologicalArea = new TechnologicalArea(request.tilte(),request.tilte(), imageUrl);
		TechnologicalArea createdTechnologicalArea = repository.save(technologicalArea);
		logger.createdObjectLog(createdTechnologicalArea);
		return createdTechnologicalArea;

	}
}
