package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.UserDto;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologialAreaRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTechnologicalArea {

	private final TechnologialAreaRepository repository;
	
	private final FindTechnologicalArea findTechnologicalArea;
	
	private final AuthChecker checker;
	
	private final Logger logger;
	
	public void delete(String ID) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(ID);
		repository.delete(technologicalArea);
		logger.info(String.format("%S deleted by %S", technologicalArea, new UserDto(checker.getUser())));
	}
}
