package com.dougfsilva.e_AGE.application.usecases.dropout;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.application.dto.response.DropoutResponse;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.dropout.Dropout;
import com.dougfsilva.e_AGE.domain.dropout.DropoutRepository;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DropoutFinder {

	private final DropoutRepository repository;
	
	public Dropout findByID(String ID) {
		return repository.findByID(ID).orElseThrow(() -> new ObjectNotFoundException(String.format("Dropout with ID %s not found!", ID)));
	}
	
	public DropoutResponse findByIDAsDropoutResponse(String ID) {
		return DropoutResponse.fromDropout(findByID(ID));
	}
	
	List<DropoutResponse> findAllByStudent(Student student){
		return repository.findAllByStudent(student).stream().map(DropoutResponse::new).collect(Collectors.toList());
	}
	
	List<DropoutResponse> findAllByClazz(Clazz clazz){
		return repository.findAllByClazz(clazz).stream().map(DropoutResponse::new).collect(Collectors.toList());
	}
	
	Page<DropoutResponse> findAllByCourse(Course course){
		return DropoutResponse.fromPage(repository.findAllByCourse(course));
	}
	
	Page<DropoutResponse> findAllByDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest){
		return DropoutResponse.fromPage(repository.findAllByDatePeriod(min, max, pageRequest));
	}
	
	Page<DropoutResponse> findAll(PageRequest pageRequest){
		return DropoutResponse.fromPage(repository.findAll(pageRequest));
	}
}
