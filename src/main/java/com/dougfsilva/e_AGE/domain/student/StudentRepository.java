package com.dougfsilva.e_AGE.domain.student;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface StudentRepository {

	Student save(Student student);
	void delete(Student student);
	Optional<Student> findByID(String ID);
	default Student findByIdOrThrow(String ID) {
	    return findByID(ID)
	        .orElseThrow(() -> new ObjectNotFoundException("Student not found for ID: " + ID));
	}
	Page<Student> findAllByClazz(Clazz clazz, PageRequest pageRequest);
	Page<Student> findAllByEnterprise(Enterprise enterprise, PageRequest pageRequest);
	Long countByEnterprise(Enterprise enterprise);
	Page<Student> findAll(PageRequest pageRequest);
	Boolean existsByClazz(Clazz clazz);
	Boolean existsByResponsible(Responsible responsible);
	Boolean existsByEnterprise(Enterprise enterprise);

	
}
