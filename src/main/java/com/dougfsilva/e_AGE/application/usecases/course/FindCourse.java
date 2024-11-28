package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.usecases.technologicalArea.FindTechnologicalArea;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.technologicalArea.TechnologicalArea;
import com.dougfsilva.e_AGE.domain.utilities.AuthChecker;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindCourse {

	private final CourseRepository repository;

	private final FindTechnologicalArea findTechnologicalArea;

	private final AuthChecker checker;

	public Course findByID(String ID) {
		checker.requireAnyProfiles(getClass());
		return repository.findByID(ID)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("Course with ID %S not found!", ID)));
	}

	public Page<Course> findAllByModality(CourseModality modality, PageRequest pageRequest) {
		checker.requireAnyProfiles(getClass());
		return repository.findAllByModality(modality, pageRequest);
	}

	public Page<Course> findAllByTechnologicalArea(String technologicalAreaID, PageRequest pageRequest) {
		TechnologicalArea technologicalArea = findTechnologicalArea.findByID(technologicalAreaID);
		return repository.findAllByTechnologicalArea(technologicalArea, pageRequest);
	}

	public Page<Course> findAll(PageRequest pageRequest) {
		return repository.findAll(pageRequest);
	}
}
