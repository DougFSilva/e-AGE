package com.dougfsilva.e_AGE.domain.occurrence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface OccurrenceRepository {

	Occurrence save(Occurrence occurrence);
	void delete(Occurrence occurrence);
	Optional<Occurrence> findByID(String ID);
	default Occurrence findByIdOrThrow(String ID) {
	    return findByID(ID)
	        .orElseThrow(() -> new ObjectNotFoundException("Occurrence not found for ID: " + ID));
	}
	Page<Occurrence> findAllByOpeningDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	Page<Occurrence> findAllByOpeningDatePeriodAndReporterUserOrOpeningDatePeriodAndRestricted(LocalDate min, LocalDate max, User reporter, Boolean restrict, PageRequest pageRequest);
	Page<Occurrence> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	Page<Occurrence> findAllByClosingDatePeriodAndReporterUserOrClosingDatePeriodAndRestricted(LocalDate min, LocalDate max, User reporter, Boolean restrict, PageRequest pageRequest);
	Page<Occurrence> findAllByReporter(Employee reporter, PageRequest pageRequest);
	Page<Occurrence> findAllByReporterAndReporterUserOrReporterAndRestricted(Employee reporter, Boolean restrict, PageRequest pageRequest);
	List<Occurrence> findAllByStudant(Student studant);
	List<Occurrence> findAllByStudantAndReporterUserOrStudentAndRestricted(Student studant, User reporter, Boolean restrict);
	Page<Occurrence> findAllByClazz(Clazz clazz, PageRequest pageRequest);
	Page<Occurrence> findAllByClazzAndReporterUserOrClazzAndRestricted(Clazz clazz, User reporter, Boolean restrict, PageRequest pageRequest);
	Page<Occurrence> findAllByOccurrenceType(OccurrenceType occurrenceType, PageRequest pageRequest);
	Page<Occurrence> findAllByOccurrenceTypeAndReporterUserOrOccurrenceTypeAndRestricted(OccurrenceType occurrenceType, User reporter, Boolean restrict, PageRequest pageRequest);
	Page<Occurrence> findAllByForwarding(Boolean forwarding, PageRequest pageRequest);
	Page<Occurrence> findAllByForwardingAndReporterUserOrForwardingAndRestricted(Boolean forwardind, Boolean restrict, User reporter, PageRequest pageRequest);
	Page<Occurrence> findAllByOpen(Boolean open, PageRequest pageRequest);
	Page<Occurrence> findAllByOpenAndReporterUserOrOpenAndRestricted(Boolean open, User reporter, Boolean restrict,PageRequest pageRequest);
	Page<Occurrence> findAll(PageRequest pageRequest);
	Page<Occurrence> findAllAndReporterUserOrRestricted(Boolean restrict, User reporter, PageRequest pageRequest);

	
}
