package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.request.CreateTechnologicalAreaRequest;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;
import com.dougfsilva.e_AGE.domain.utilities.StandardLogger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTechnologicalArea {

	private final TechnologicalAreaRepository repository;

	private final AuthChecker checker;

	private final Logger logger;

	public TechnologicalArea create(CreateTechnologicalAreaRequest request) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		TechnologicalArea technologicalArea = new TechnologicalArea(request.tilte(),request.tilte());
		TechnologicalArea createdTechnologicalArea = repository.save(technologicalArea);
		StandardLogger.createdObjectLogger(createdTechnologicalArea, checker, logger);
		return createdTechnologicalArea;

	}
}
