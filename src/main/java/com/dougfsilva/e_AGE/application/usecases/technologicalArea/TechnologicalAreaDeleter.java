package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaOperationException;
import com.dougfsilva.e_AGE.domain.exception.TechnologicalAreaValidationException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TechnologicalAreaDeleter {

	private final TechnologicalAreaRepository repository;
	private final CourseRepository courseRepository;
	private final ImageStorageService imageService ;
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			TechnologicalArea technologicalArea = repository.findByIdOrThrow(ID);
			ensureIsNoCoursesInTechnologicalArea(technologicalArea);
			repository.delete(technologicalArea);
			imageService.deleteImage(ImageType.TECHNOLOGICAL_AREA, ImageNameGenerator.byTechnologicalArea(technologicalArea));
			logger.info(String.format("Deleted Technological Area ID %s - %s", technologicalArea.getID(), technologicalArea.getTitle()));
		} catch (ObjectNotFoundException | TechnologicalAreaValidationException e) {
			String message = String.format("Error while deleting technological area ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		}catch (Exception e) {
			String message = String.format("Unexpected error when deleting technological area ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new TechnologicalAreaOperationException(message, e);
		}
	}
	
	public void ensureIsNoCoursesInTechnologicalArea(TechnologicalArea technologicalArea) {
		if (courseRepository.existsByTechnologialArea(technologicalArea)) {
			throw new TechnologicalAreaValidationException	(
					String.format("The technological area %s cannot be deleted because there are courses still associated with it!", 
							technologicalArea.getTitle()));
		}
	}
}
