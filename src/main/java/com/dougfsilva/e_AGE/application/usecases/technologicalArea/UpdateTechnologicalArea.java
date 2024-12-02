package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.dto.request.TechnologicalAreaDataRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTechnologicalArea {

	private final TechnologicalAreaRepository repository;

	private final FindTechnologicalArea findTechnologicalArea;
	
	private final StoreImage storeImage;

	private final StandardLogger logger;

	public TechnologicalArea update(String ID, TechnologicalAreaDataRequest request) {
		TechnologicalArea area = findTechnologicalArea.findByID(ID);
		if(request.image() != null && !request.image().isEmpty()) {
			String imageUrl = storeImage.execute(request.image(), ImageType.TECHNOLOGICAL_AREA, request.title());
			area.setImage(imageUrl);
		}
		
		if(request.title() != null && !request.title().isBlank()) {
			area.setTitle(request.title());
		}
		if(request.description() != null && !request.description().isBlank()) {
			area.setDescription(request.description());
		}
		TechnologicalArea updatedArea = repository.save(area);
		logger.updatedObjectLog(updatedArea);
		return updatedArea;
	}
}
