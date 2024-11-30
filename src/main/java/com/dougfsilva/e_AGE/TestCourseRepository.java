package com.dougfsilva.e_AGE;

import java.util.Optional;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public class TestCourseRepository implements CourseRepository{

	@Override
	public Course save(Course course) {
		course.setID("1");
		return course;
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
	public Page<Course> findAll(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}


}
