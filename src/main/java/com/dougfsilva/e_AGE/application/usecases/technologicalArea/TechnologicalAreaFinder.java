package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TechnologicalAreaFinder {

	private final TechnologicalAreaRepository repository;

	public Page<TechnologicalArea> findAllByTitleContains(String title, PageRequest pageRequest) {
		return repository.findAllByTitleContains(title, pageRequest);
	}

	public Page<TechnologicalArea> findAll(PageRequest pageRequest) {
		return repository.findAll(pageRequest);
	}
}
