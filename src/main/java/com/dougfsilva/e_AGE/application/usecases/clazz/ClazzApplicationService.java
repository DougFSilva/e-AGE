package com.dougfsilva.e_AGE.application.usecases.clazz;

import java.time.LocalDate;

import com.dougfsilva.e_AGE.application.dto.request.CreateClazzRequest;
import com.dougfsilva.e_AGE.application.dto.request.UpdateClazzRequest;
import com.dougfsilva.e_AGE.application.dto.response.ClazzResponse;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface ClazzApplicationService {

	ClazzResponse create(CreateClazzRequest request);
	
	void delete(String ID);
	
	ClazzResponse update(UpdateClazzRequest request);
	
	ClazzResponse findByID(String ID);
	
	Page<ClazzResponse> findAllByNameContains(String name, PageRequest pageRequest);
	
	Page<ClazzResponse> findAllByCourse(String courseID, PageRequest pageRequest);
	
	Page<ClazzResponse> findAllByCreationDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);

	Page<ClazzResponse> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);
	
	Page<ClazzResponse> findAll(PageRequest pageRequest);
}
