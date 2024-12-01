package com.dougfsilva.e_AGE.application.usecases.clazz;

import com.dougfsilva.e_AGE.application.usecases.utilities.DeleteImage;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteClazz {

	private final ClazzRepository repository;

	private final StudentRepository studentRepository;

	private final FindClazz findClazz;

	private final DeleteImage deleteImage;

	private final StandardLogger logger;

	public void execute(String ID) {
		Clazz clazz = findClazz.findByID(ID);
		studentRepository.deleteAllByClazz(clazz);
		deleteImage.execute(clazz.getImage(), ImageType.CLAZZ);
		repository.delete(clazz);
		logger.deletedObjectLog(clazz);
	}

}
