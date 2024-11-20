package com.dougfsilva.e_AGE.domain.course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

	Course create(Course course);
	
	void delete(Course course);
	
	Course update(Course course);
	
	Optional<Course> findByID(String ID);
	
	List<Course> findByModality(CourseModality modality);
	
	List<Course> findByTechnologicalArea(TechnologicalArea technologicalArea);
	
	List<Course> findAll();
}
