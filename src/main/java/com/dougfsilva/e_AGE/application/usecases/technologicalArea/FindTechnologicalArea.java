package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTechnologicalArea {

	private final TechnologicalAreaRepository repository;

	private final AuthChecker checker;

	public TechnologicalArea findByID(String ID) {
		checker.requireAnyProfiles(getClass());
		return repository.findByID(ID).orElseThrow(
				() -> new ObjectNotFoundException(String.format("Technological Area with ID %S not found!", ID)));
	}

	public Page<TechnologicalArea> findAllByTitleContains(String title, PageRequest pageRequest) {
		checker.requireAnyProfiles(getClass());
		return repository.findAllByTitleContains(title, pageRequest);
	}

	public Page<TechnologicalArea> findAll(PageRequest pageRequest) {
		checker.requireAnyProfiles(getClass());
		return repository.findAll(pageRequest);
	}
}
