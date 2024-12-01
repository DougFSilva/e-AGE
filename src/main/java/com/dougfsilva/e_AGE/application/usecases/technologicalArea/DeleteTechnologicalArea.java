package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.usecases.utilities.DeleteImage;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTechnologicalArea {

	private final TechnologicalAreaRepository repository;
	
	private final FindTechnologicalArea findTechnologicalArea;
	
	private final DeleteImage deleteImage ;
	
	private final StandardLogger logger;
	
	public void delete(String ID) {
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(ID);
		repository.delete(technologicalArea);
		deleteImage.execute(ID, ImageType.TECHNOLOGICAL_AREA);
		logger.deletedObjectLog(technologicalArea);
	}
}
