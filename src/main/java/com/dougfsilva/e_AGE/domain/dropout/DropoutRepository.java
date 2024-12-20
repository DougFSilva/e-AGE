package com.dougfsilva.e_AGE.domain.dropout;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface DropoutRepository {

	Dropout save(Dropout dropout);
	void delete(Dropout dropout);
	void deleleAllBydeleteAllByStudent(Student student);
	Optional<Dropout> findByID(String ID);
	default Dropout findByIdOrThrow(String ID) {
	    return findByID(ID)
	        .orElseThrow(() -> new ObjectNotFoundException("Dropout not found for ID: " + ID));
	}
	List<Dropout> findAllByStudent(Student student);
	List<Dropout> findAllByClazz(Clazz clazz);
	Page<Dropout> findAllByCourse(Course course);
	Page<Dropout> findAllByDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	Page<Dropout> findAll(PageRequest pageRequest);
	Boolean existsByClazz(Clazz clazz);
}
