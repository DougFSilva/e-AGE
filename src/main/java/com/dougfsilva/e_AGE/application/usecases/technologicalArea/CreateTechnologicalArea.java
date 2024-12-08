package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.dto.request.CreateTechnologicalAreaRequest;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.application.usecases.utilities.StoreImage;
import com.dougfsilva.e_AGE.domain.exception.DataIntegrityViolationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTechnologicalArea {

	private final TechnologicalAreaRepository repository;
	
	private final StoreImage storeImage;

	private final StandardLogger logger;

	public TechnologicalArea execute(CreateTechnologicalAreaRequest request) {
		repository.findByTitle(request.getTitle().toUpperCase()).ifPresent(t -> {
			throw new DataIntegrityViolationException(String.format("Technological Area with title %S already exists!", t.getTitle()));
		});
		String imageUrl = storeImage.execute(request.getImage(), ImageType.TECHNOLOGICAL_AREA, request.getTitle());
		TechnologicalArea technologicalArea = new TechnologicalArea(request.getTitle(),request.getDescription(), imageUrl);
		TechnologicalArea createdTechnologicalArea = repository.save(technologicalArea);
		logger.info(String.format("Create Technologial Area ID %S - %S",createdTechnologicalArea.getID(), createdTechnologicalArea.getTitle()));
		return createdTechnologicalArea;

	}
}
