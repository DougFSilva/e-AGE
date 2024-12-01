package com.dougfsilva.e_AGE.domain.clazz;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface ClazzRepository {

	Clazz save(Clazz clazz);

	void delete(Clazz clazz);

	Optional<Clazz> findByID(String ID);
	
	Page<Clazz> findAllByNameContains(String name, PageRequest pageRequest);
	
	Page<Clazz> findAllByCourse(Course course, PageRequest pageRequest);
	
	Long countByCourse(Course course);
	
	List<Clazz> findAllByStudent(Student student);
	
	Page<Clazz> findAll(PageRequest pageRequest);
	
	Page<Clazz> findAll();

}
