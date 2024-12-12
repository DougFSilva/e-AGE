package com.dougfsilva.e_AGE.application.usecases.occurrence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceType;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceFinder {

	private final OccurrenceRepository repository;
	
	public OccurrenceResponse findByIDA(String ID) {
		return OccurrenceResponse.fromOccurrence(repository.findByIdOrThrow(ID));
	}
	
	Page<OccurrenceResponse> findAllByOpeningDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest){
		return OccurrenceResponse.fromPage(repository.findAllByOpeningDatePeriod(min, max, pageRequest));
	}
	
	Page<OccurrenceResponse> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest){
		return OccurrenceResponse.fromPage(repository.findAllByClosingDatePeriod(min, max, pageRequest));
	}
	
	Page<OccurrenceResponse> findAllByReporter(Employee reporter, PageRequest pageRequest){
		return OccurrenceResponse.fromPage(repository.findAllByReporter(reporter, pageRequest));
	}
	
	List<OccurrenceResponse> findAllByStudant(Student studant, PageRequest pageRequest){
		return repository.findAllByStudant(studant).stream().map(OccurrenceResponse::new).collect(Collectors.toList());
	}
	
	Page<OccurrenceResponse> findAllByClazz(Clazz clazz, PageRequest pageRequest){
		return OccurrenceResponse.fromPage(repository.findAllByClazz(clazz, pageRequest));
	}
	
	Page<OccurrenceResponse> findAllByOccurrenceType(OccurrenceType occurrenceType, PageRequest pageRequest){
		return OccurrenceResponse.fromPage(repository.findAllByOccurrenceType(occurrenceType, pageRequest));
	}
	
	Page<OccurrenceResponse> findAllByRestricted(Boolean restricted, PageRequest pageRequest){
		return OccurrenceResponse.fromPage(repository.findAllByRestricted(restricted, pageRequest));
	}
	
	Page<OccurrenceResponse> findAllByForwarding(Boolean forwarding, PageRequest pageRequest){
		return OccurrenceResponse.fromPage(repository.findAllByForwarding(forwarding, pageRequest));
	}
	
	Page<OccurrenceResponse> findAllByOpen(Boolean open, PageRequest pageRequest){
		return OccurrenceResponse.fromPage(repository.findAllByOpen(open, pageRequest));
	}
	
	Page<OccurrenceResponse> findAll(PageRequest pageRequest){
		return OccurrenceResponse.fromPage(repository.findAll(pageRequest));
	}
	
}
