package com.dougfsilva.e_AGE.application.usecases.course;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.application.dto.request.CreateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.request.UpdateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface CourseApplicationService {

	CourseResponse create(CreateCourseRequest request);

	void delete(String ID);

	CourseResponse update(UpdateCourseRequest request);
	
	CourseResponse closeCourse(String ID, LocalDate date);
	
	CourseResponse reopenCourse(String ID);

	CourseResponse findByID(String ID);

	Page<CourseResponse> findAllByModality(CourseModality modality, PageRequest pageRequest);

	Page<CourseResponse> findAllByTechnologicalArea(String technologicalAreaID, PageRequest pageRequest);
	
	Page<CourseResponse> findAllByCreationDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	
	Page<CourseResponse> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);

	Page<CourseResponse> findAll(PageRequest pageRequest);
}
