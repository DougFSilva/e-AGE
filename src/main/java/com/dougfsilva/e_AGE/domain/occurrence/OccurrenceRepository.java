package com.dougfsilva.e_AGE.domain.occurrence;

import java.time.LocalDate;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface OccurrenceRepository {

	Occurrence save(Occurrence occurrence);
	
	void delete(Occurrence occurrence);
	
	Optional<Occurrence> findByID(String ID);
	
	Page<Occurrence> findAllByOpeningDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	
	Page<Occurrence> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	
	Page<Occurrence> findAllByReporter(Employee reporter, PageRequest pageRequest);
	
	Page<Occurrence> findAllByStudant(Student studant, PageRequest pageRequest);
	
	Page<Occurrence> findAllByClazz(Clazz clazz, PageRequest pageRequest);
	
	Page<Occurrence> findAllByOccurrenceType(OccurrenceType occurrenceType, PageRequest pageRequest);
	
	Page<Occurrence> findAllByRestricted(Boolean restricted, PageRequest pageRequest);
	
	Page<Occurrence> findAllByForwarding(Boolean forwarding, PageRequest pageRequest);
	
	Page<Occurrence> findAllByOpen(Boolean open, PageRequest pageRequest);
	
	Page<Occurrence> findAll(PageRequest pageRequest);
	
}
