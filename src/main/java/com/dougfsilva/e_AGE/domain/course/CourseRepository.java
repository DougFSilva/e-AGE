package com.dougfsilva.e_AGE.domain.course;

import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface CourseRepository {

	Course create(Course course);
	
	void delete(Course course);
	
	Course update(Course course);
	
	Optional<Course> findByID(String ID);
	
	List<Course> findAllByModality(CourseModality modality);
	
	List<Course> findAllByTechnologicalArea(TechnologicalArea technologicalArea, PageRequest pageRequest);
	
	List<Course> findAll(PageRequest pageRequest);
}
