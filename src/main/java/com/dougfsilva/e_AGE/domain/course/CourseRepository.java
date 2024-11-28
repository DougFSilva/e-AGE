package com.dougfsilva.e_AGE.domain.course;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface CourseRepository {

	Course save(Course course);
	
	void delete(Course course);
	
	Optional<Course> findByID(String ID);
	
	Page<Course> findAllByModality(CourseModality modality, PageRequest pageRequest);
	
	Page<Course> findAllByTechnologicalArea(TechnologicalArea technologicalArea, PageRequest pageRequest);
	
	Page<Course> findAll(PageRequest pageRequest);
}
