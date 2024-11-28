package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import java.util.Arrays;

import com.dougfsilva.e_AGE.application.dto.UserDto;
import com.dougfsilva.e_AGE.application.dto.request.UpdateTechnologicalAreaRequest;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTechnologicalArea {

	private final TechnologicalAreaRepository repository;

	private final FindTechnologicalArea findTechnologicalArea;

	private final AuthChecker checker;

	private final Logger logger;

	public TechnologicalArea update(UpdateTechnologicalAreaRequest request) {
		checker.requireProfiles(getClass(), Arrays.asList(ProfileType.ADMIN));
		TechnologicalArea area = findTechnologicalArea.findByID(request.ID());
		area.setTitle(request.tilte());
		area.setDescription(request.description());
		TechnologicalArea updatedArea = repository.save(area);
		logger.info(String.format("%S updated by %S", updatedArea, new UserDto(checker.getUser())));
		return updatedArea;
	}
}
