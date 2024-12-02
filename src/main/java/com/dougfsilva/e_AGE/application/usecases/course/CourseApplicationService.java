package com.dougfsilva.e_AGE.application.usecases.course;

import com.dougfsilva.e_AGE.application.dto.request.CourseDataRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface CourseApplicationService {

	CourseResponse create(CourseDataRequest request);

	void delete(String ID);

	CourseResponse update(String ID, CourseDataRequest request);

	CourseResponse findByID(String ID);

	Page<CourseResponse> findAllByModality(CourseModality modality, PageRequest pageRequest);

	Page<CourseResponse> findAllByTechnologicalArea(String technologicalAreaID, PageRequest pageRequest);

	Page<CourseResponse> findAll(PageRequest pageRequest);
}
