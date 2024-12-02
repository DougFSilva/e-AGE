package com.dougfsilva.e_AGE.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dougfsilva.e_AGE.application.dto.request.CourseDataRequest;
import com.dougfsilva.e_AGE.application.dto.response.CourseResponse;
import com.dougfsilva.e_AGE.application.usecases.course.CourseApplicationService;
import com.dougfsilva.e_AGE.application.usecases.course.CreateCourse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseApplicationServiceImpl implements CourseApplicationService {
	
	private final CreateCourse createCourse;

	@Override
	@Transactional
	public CourseResponse createCourse(CourseDataRequest request) {
		return createCourse.create(request);
	}

	@Override
	public void deleteResponse(String ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CourseResponse updateCourse(String ID, CourseDataRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
