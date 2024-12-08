package com.dougfsilva.e_AGE.domain.course;

import java.time.LocalDate;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface CourseRepository {

	Course save(Course course);
	
	void delete(Course course);
	
	Optional<Course> findByID(String ID);
	
	Optional<Course> findByTitle(String title);
	
	Page<Course> findAllByModality(CourseModality modality, PageRequest pageRequest);
	
	Page<Course> findAllByTechnologicalArea(TechnologicalArea technologicalArea, PageRequest pageRequest);
	
	Page<Course> findAllByCreationDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	
	Page<Course> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	
	Page<Course> findAll(PageRequest pageRequest);
	
	Boolean existsByTechnologialArea(TechnologicalArea technologicalArea);
	
	Boolean existsByTitle(String title);
	
}
