package com.dougfsilva.e_AGE.infrastructure;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.dougfsilva.e_AGE.application.dto.request.CreateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.request.UpdateCourseRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.course.CourseApplicationService;
import com.dougfsilva.e_AGE.application.usecases.course.CreateCourse;
import com.dougfsilva.e_AGE.domain.course.CourseModality;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseApplicationServiceImpl implements CourseApplicationService {
	
	private final CreateCourse createCourse;

	@Override
	public CourseResponse create(CreateCourseRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CourseResponse update(UpdateCourseRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseResponse closeCourse(String ID, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseResponse reopenCourse(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseResponse findByID(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CourseResponse> findAllByModality(CourseModality modality, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CourseResponse> findAllByTechnologicalArea(String technologicalAreaID, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CourseResponse> findAllByCreationDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CourseResponse> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CourseResponse> findAll(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}


}
