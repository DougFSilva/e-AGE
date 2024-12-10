package com.dougfsilva.e_AGE.domain.enrollment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface EnrollmentRepository {

	Enrollment save(Enrollment enrollment);
	void delete(String ID);
	Optional<Enrollment> findByID(String ID);
	List<Enrollment> findAllByStudent(Student student);
	List<Enrollment> findAllByClazz(Clazz clazz);
	Page<Enrollment> findAllByCourse(Course course);
	Page<Enrollment> findAllByDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	Page<Enrollment> findAllByStatus(EnrollmentStatus status, PageRequest request);
	Page<Enrollment> findAll(PageRequest pageRequest);
	Boolean existsByClazz(Clazz clazz);
	Boolean existsByClazzAndStudent(Student student, Clazz clazz);
	Boolean existsByRegistration(String registration);
	
}
