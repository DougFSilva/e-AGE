package com.dougfsilva.e_AGE.domain.clazz;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.pagination.PageRequest;
import com.dougfsilva.e_AGE.domain.student.Studant;

public interface ClazzRepository {

	Clazz create(Clazz clazz);

	void delete(Clazz clazz);

	Clazz update(Clazz clazz, Clazz updatedClazz);
	
	Optional<Clazz> findByID(String ID);
	
	List<Clazz> findAllByNameContains(String name, PageRequest pageRequest);
	
	List<Clazz> findAllByCourse(Course course, PageRequest pageRequest);
	
	List<Clazz> findAllByStudant(Studant studant);
	
	List<Clazz> findAll(PageRequest pageRequest);

}
