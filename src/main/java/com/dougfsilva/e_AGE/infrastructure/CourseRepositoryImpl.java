package com.dougfsilva.e_AGE.infrastructure;

import java.time.LocalDate;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public class CourseRepositoryImpl implements CourseRepository{

	@Override
	public Course save(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Course> findByID(String ID) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Course> findByTitle(String title) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Page<Course> findAllByModality(CourseModality modality, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Course> findAllByTechnologicalArea(TechnologicalArea technologicalArea, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Course> findAllByCreationDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Course> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Course> findAll(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsByTechnologialArea(TechnologicalArea technologicalArea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}




}
