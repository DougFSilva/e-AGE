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
public class CreateTechnologicalArea {

	private final TechnologialAreaRepository repository;

	private final AuthChecker checker;

	private final Logger logger;

	public TechnologicalArea create(String title, String description) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		TechnologicalArea technologicalArea = new TechnologicalArea(title, description);
		TechnologicalArea createdTechnologicalArea = repository.create(technologicalArea);
		logger.info(String.format("%S created by %S", createdTechnologicalArea, new UserDto(checker.getUser())));
		return createdTechnologicalArea;

	}
}
