package com.dougfsilva.e_AGE.application.usecases.course;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalAreaRepository;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseFinder {

	private final CourseRepository repository;
	private final TechnologicalAreaRepository technologicalAreaRepository;

	public CourseResponse findByID(String ID) {
		return CourseResponse.fromCourse(repository.findByIdOrThrow(ID));
	}

	public Page<CourseResponse> findAllByModality(CourseModality modality, PageRequest pageRequest) {
		return CourseResponse.fromPage(repository.findAllByModality(modality, pageRequest));
	}

	public Page<CourseResponse> findAllByTechnologicalArea(String technologicalAreaID, PageRequest pageRequest) {
		TechnologicalArea technologicalArea = technologicalAreaRepository.findByIdOrThrow(technologicalAreaID);
		return CourseResponse.fromPage(repository.findAllByTechnologicalArea(technologicalArea, pageRequest));
	}

	Page<CourseResponse> findAllByCreationDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest){
		return CourseResponse.fromPage(repository.findAllByCreationDatePeriod(min, max, pageRequest));
	}
	
	Page<CourseResponse> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest){
		return CourseResponse.fromPage(repository.findAllByClosingDatePeriod(min, max, pageRequest));
	}
	
	public Page<CourseResponse> findAll(PageRequest pageRequest) {
		return CourseResponse.fromPage(repository.findAll(pageRequest));
	}
	
}
