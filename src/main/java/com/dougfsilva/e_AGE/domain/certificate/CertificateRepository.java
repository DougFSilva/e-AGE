package com.dougfsilva.e_AGE.domain.certificate;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface CertificateRepository {

	Certificate save(Certificate certificate);

	void delete(String ID);

	Certificate findByID(String ID);

	List<Certificate> findAllByStudent(Student student);

	Page<Certificate> findAllByCourse(Course course, PageRequest pageRequest);
	
	Page<Enrollment> findAllByDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);

	Page<Certificate> findAll(PageRequest pageRequest);
	
	Boolean existsByCourse(Course course);

}
