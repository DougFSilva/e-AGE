package com.dougfsilva.e_AGE.domain.clazz;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface ClazzRepository {

	Clazz save(Clazz clazz);
	void delete(Clazz clazz);
	Optional<Clazz> findByID(String ID);
	default Clazz findByIdOrThrow(String ID) {
	    return findByID(ID)
	        .orElseThrow(() -> new ObjectNotFoundException("Clazz not found for ID: " + ID));
	}
	Optional<Clazz> findByCode(String code);
	Page<Clazz> findAllByNameContains(String name, PageRequest pageRequest);
	Page<Clazz> findAllByCourse(Course course, PageRequest pageRequest);
	List<Clazz> findAllByCourse(Course course);
	Page<Clazz> findAllByCreationDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	Page<Clazz> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	Page<Clazz> findAll(PageRequest pageRequest);
	Boolean existsByCourse(Course course);
	Boolean existsByCode(String code);

}
