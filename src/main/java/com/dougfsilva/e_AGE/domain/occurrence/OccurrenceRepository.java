package com.dougfsilva.e_AGE.domain.occurrence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface OccurrenceRepository {

	Occurrence create(Occurrence occurrence);
	
	void delete(Occurrence occurrence);
	
	Occurrence update(Occurrence occurrence);
	
	Optional<Occurrence> findByID(String ID);
	
	List<Occurrence> findAllByOpeningDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	
	List<Occurrence> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	
	List<Occurrence> findAllByReporter(Employee reporter, PageRequest pageRequest);
	
	List<Occurrence> findAllByStudant(Student studant, PageRequest pageRequest);
	
	List<Occurrence> findAllByClazz(Clazz clazz, PageRequest pageRequest);
	
	List<Occurrence> findAllByOccurrenceType(OccurrenceType occurrenceType, PageRequest pageRequest);
	
	List<Occurrence> findAllByRestricted(Boolean restricted, PageRequest pageRequest);
	
	List<Occurrence> findAllByForwarding(Boolean forwarding, PageRequest pageRequest);
	
	List<Occurrence> findAllByOpen(Boolean open);
	
	List<Occurrence> findAll(PageRequest pageRequest);
	 
}
