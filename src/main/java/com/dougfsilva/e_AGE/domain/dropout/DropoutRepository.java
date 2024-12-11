package com.dougfsilva.e_AGE.domain.dropout;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface DropoutRepository {

	Dropout save(Dropout dropout);
	void delete(String ID);
	Dropout findByID(String ID);
	List<Dropout> findAllByStudent(Student student);
	List<Dropout> findAllByClazz(Clazz clazz);
	Page<Enrollment> findAllByCourse(Course course);
	Page<Enrollment> findAllByDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	Page<Enrollment> findAll(PageRequest pageRequest);
	Boolean existsByClazz(Clazz clazz);
}
