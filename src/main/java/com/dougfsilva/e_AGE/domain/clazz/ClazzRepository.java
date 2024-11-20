package com.dougfsilva.e_AGE.domain.clazz;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.course.Course;

public interface ClazzRepository {

	Clazz create(Clazz clazz);

	void delete(Clazz clazz);

	Clazz update(Clazz clazz);
	
	Optional<Clazz> findByID(String ID);
	
	List<Clazz> findByNameContains(String name);
	
	List<Clazz> findByCourse(Course course);
	
	List<Clazz> findAll();

}
