package com.dougfsilva.e_AGE.application.usecases.enterprise;

import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnterpriseFinder {

	private final EnterpriseRepository repository;
	
	public Page<Enterprise> findAllByNameContains(String name, PageRequest pageRequest){
		return repository.findAllByNameContains(name, pageRequest);
	}
	
	public Page<Enterprise> findAll(PageRequest pageRequest){
		return repository.findAll(pageRequest);
	}
	
}
