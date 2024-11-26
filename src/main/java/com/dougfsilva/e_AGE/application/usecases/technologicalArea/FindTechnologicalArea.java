package com.dougfsilva.e_AGE.application.usecases.technologicalArea;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologialAreaRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Direction;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTechnologicalArea {

	private final TechnologialAreaRepository repository;

	private final AuthChecker checker;

	public TechnologicalArea findByID(String ID) {
		checker.requireAnyProfiles(getClass());
		Optional<TechnologicalArea> technologicalArea = repository.findByID(ID);
		return technologicalArea.orElseThrow(() -> new ObjectNotFoundException(String.format("Technological Area with ID %S not found!", ID)));
	}
	
	public List<TechnologicalArea> findAllByTitleContains(String title, Integer page, Integer size, Direction direction){
		checker.requireAnyProfiles(getClass());
		PageRequest pageRequest = new PageRequest(page, size, direction);
		return repository.findAllByTitleContains(title, pageRequest);
	}
	
	public List<TechnologicalArea> findAll(Integer page, Integer size, Direction direction){
		checker.requireAnyProfiles(getClass());
		PageRequest pageRequest = new PageRequest(page, size, direction);
		return repository.findAll(pageRequest);
	}
}
