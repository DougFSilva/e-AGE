package com.dougfsilva.e_AGE.domain.student;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.guardian.Guardian;
import com.dougfsilva.e_AGE.domain.pagination.PageRequest;

public interface StudantRepository {

	Studant create(Studant studant);
	
	void delete(Studant studant);
	
	Studant update(Studant studant, Studant updatedStudant);
	
	Optional<Studant> findByID(String ID);
	
	List<Studant> findAllByClazz(Clazz clazz, PageRequest pageRequest);
	
	List<Studant> findAllByGuardian(Guardian guardian, PageRequest pageRequest);
	
	List<Studant> findAllByEnterprise(Enterprise enterprise, PageRequest pageRequest);
	
	List<Studant> findAll(PageRequest pageRequest);
}
