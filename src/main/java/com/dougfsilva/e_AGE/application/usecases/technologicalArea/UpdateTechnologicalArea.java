package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.dto.request.UpdateTechnologicalAreaRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTechnologicalArea {

	private final TechnologicalAreaRepository repository;

	private final FindTechnologicalArea findTechnologicalArea;
	
	private final StoreImage storeImage;

	private final StandardLogger logger;

	public TechnologicalArea update(UpdateTechnologicalAreaRequest request) {
		TechnologicalArea area = findTechnologicalArea.findByID(request.getID());
		if(!request.getTitle().equalsIgnoreCase(area.getTitle()) && request.getTitle() != null && !request.getTitle().isBlank()) {
			repository.findByTitle(request.getTitle().toUpperCase()).ifPresent(t -> {
				throw new DataIntegrityViolationException(String.format("Tehcnologial Area with title %S already exists!", t.getTitle()));
			});
			area.setTitle(request.getTitle());
		}
		if(request.getDescription() != null && !request.getDescription().isBlank()) {
			area.setDescription(request.getDescription());
		}
		if(request.getImage() != null && !request.getImage().isEmpty()) {
			String imageUrl = storeImage.execute(request.getImage(), ImageType.TECHNOLOGICAL_AREA, request.getTitle());
			area.setImage(imageUrl);
		}
		TechnologicalArea updatedArea = repository.save(area);
		logger.updatedObjectLog(updatedArea);
		return updatedArea;
	}
}
