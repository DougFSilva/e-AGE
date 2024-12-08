package com.dougfsilva.e_AGE.application.usecases.course;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCourse {

	private final CourseRepository repository;

	private final FindTechnologicalArea findTechnologicalArea;

	public Course findByID(String ID) {
		return repository.findByID(ID)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("Course with ID %S not found!", ID)));
	}

	public CourseResponse findByIDAsCourseResponse(String ID) {
		return CourseResponse.fromCourse(findByID(ID));
	}

	public Page<CourseResponse> findAllByModality(CourseModality modality, PageRequest pageRequest) {
		return CourseResponse.fromPage(repository.findAllByModality(modality, pageRequest));
	}

	public Page<CourseResponse> findAllByTechnologicalArea(String technologicalAreaID, PageRequest pageRequest) {
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(technologicalAreaID);
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
