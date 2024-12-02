package com.dougfsilva.e_AGE.application.usecases.clazz;

import java.util.List;

import com.dougfsilva.e_AGE.application.dto.request.ClazzDataRequest;
import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface ClazzApplicationService {

	ClazzResponse create(ClazzDataRequest request);
	
	void delete(String ID);
	
	ClazzResponse update(String ID, ClazzDataRequest request);
	
	ClazzResponse findByID(String ID);
	
	Page<ClazzResponse> findAllByNameContains(String name, PageRequest pageRequest);
	
	Page<ClazzResponse> findAllByCourse(String courseID, PageRequest pageRequest);
	
	List<ClazzResponse> findAllByStudent(String studentID);
	
	Page<ClazzResponse> findAll(PageRequest pageRequest);
}
