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
		String imageUrl = storeImage.execute(request.image(), ImageType.TECHNOLOGICAL_AREA, request.tilte());
		TechnologicalArea area = findTechnologicalArea.findByID(ID);
		area.setTitle(request.tilte());
		area.setDescription(request.description());
		area.setImage(imageUrl);
		TechnologicalArea updatedArea = repository.save(area);
		logger.updatedObjectLog(updatedArea);
		return updatedArea;
	}
}
