package com.dougfsilva.e_AGE.domain.student;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.guardian.Guardian;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface StudentRepository {

	Student save(Student student);
	
	void delete(Student student);
	
	void deleteAllByClazz(Clazz clazz);
	
	Optional<Student> findByID(String ID);
	
	Page<Student> findAllByClazz(Clazz clazz, PageRequest pageRequest);
	
	Page<Student> findAllByGuardian(Guardian guardian, PageRequest pageRequest);
	
	Page<Student> findAllByEnterprise(Enterprise enterprise, PageRequest pageRequest);
	
	Page<Student> findAll(PageRequest pageRequest);
}
