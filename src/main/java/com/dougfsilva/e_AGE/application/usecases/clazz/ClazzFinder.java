package com.dougfsilva.e_AGE.application.usecases.clazz;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.application.usecases.course.CourseFinder;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClazzFinder {
	
	private final ClazzRepository repository;
	private final CourseFinder findCourse;
	
	public Clazz findByID(String ID) {
		return repository.findByID(ID).orElseThrow(
				() -> new ObjectNotFoundException(String.format("Clazz with ID %s not found!", ID)));
	}
	
	public ClazzResponse findByIDAsClazzResponse(String ID) {
		return ClazzResponse.fromClazz(findByID(ID));
	}
	
	public Page<ClazzResponse> findAllByNameContains(String name, PageRequest pageRequest){
		return ClazzResponse.fromPage(repository.findAllByNameContains(name, pageRequest));
	}
	
	public Page<ClazzResponse> findAllByCourse(String courseID, PageRequest pageRequest){
		Course course = findCourse.findByID(courseID);
		return ClazzResponse.fromPage(repository.findAllByCourse(course, pageRequest));
	}
	
	public List<Clazz> findAllByCourse(String courseID){
		Course course = findCourse.findByID(courseID);
		return repository.findAllByCourse(course);
	}
	
	Page<ClazzResponse> findAllByCreationDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest){
		return ClazzResponse.fromPage(repository.findAllByCreationDatePeriod(min, max, pageRequest));
	}

	Page<ClazzResponse> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest){
		return  ClazzResponse.fromPage(repository.findAllByClosingDatePeriod(min, max, pageRequest));
	}
	
	public Page<ClazzResponse> findAll(PageRequest pageRequest){
		return ClazzResponse.fromPage(repository.findAll(pageRequest));
	}

}
