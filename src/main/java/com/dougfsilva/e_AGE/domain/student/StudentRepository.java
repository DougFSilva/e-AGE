package com.dougfsilva.e_AGE.domain.student;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.guardian.Guardian;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface StudentRepository {

	Student create(Student student);
	
	void delete(Student student);
	
	Student update(Student student, Student updatedStudent);
	
	Optional<Student> findByID(String ID);
	
	List<Student> findAllByClazz(Clazz clazz, PageRequest pageRequest);
	
	List<Student> findAllByGuardian(Guardian guardian, PageRequest pageRequest);
	
	List<Student> findAllByEnterprise(Enterprise enterprise, PageRequest pageRequest);
	
	List<Student> findAll(PageRequest pageRequest);
}
