package com.dougfsilva.e_AGE.domain.certificate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface CertificateRepository {

	Certificate save(Certificate certificate);
	void delete(Certificate certificate);
	Optional<Certificate> findByID(String ID);
	default Certificate findByIdOrThrow(String ID) {
	    return findByID(ID)
	        .orElseThrow(() -> new ObjectNotFoundException("Certificate not found for ID: " + ID));
	}
	List<Certificate> findAllByStudent(Student student);
	Page<Certificate> findAllByCourse(Course course, PageRequest pageRequest);
	Page<Certificate> findAllByDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	Page<Certificate> findAll(PageRequest pageRequest);
	Boolean existsByCourse(Course course);

}
