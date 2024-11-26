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
public class UpdateTechnologicalArea {

	private final TechnologialAreaRepository repository;

	private final FindTechnologicalArea findTechnologicalArea;

	private final AuthChecker checker;

	private final Logger logger;

	public TechnologicalArea update(String ID, String title, String description) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		TechnologicalArea area = findTechnologicalArea.findByID(ID);
		area.setTitle(title);
		area.setDescription(description);
		TechnologicalArea updatedArea = repository.update(area);
		logger.info(String.format("%S updated by %S", updatedArea, new UserDto(checker.getUser())));
		return updatedArea;
	}
}
