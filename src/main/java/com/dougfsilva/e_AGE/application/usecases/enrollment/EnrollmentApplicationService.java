package com.dougfsilva.e_AGE.application.usecases.enrollment;

import java.time.LocalDate;
import java.util.List;

import com.dougfsilva.e_AGE.application.dto.request.EnrollRequest;
import com.dougfsilva.e_AGE.application.dto.response.EnrollmentResponse;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

public interface EnrollmentApplicationService {

	EnrollmentResponse enroll(EnrollRequest request);
	
	void disenroll(String ID);
	
	public EnrollmentResponse findByID(String ID);

	List<EnrollmentResponse> findAllByStudent(String studentID);

	List<EnrollmentResponse> findAllByClazz(String clazzID);

	Page<EnrollmentResponse> findAllByCourse(String courseID, PageRequest pageRequest);

	Page<EnrollmentResponse> findAllByDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest);

	Page<EnrollmentResponse> findAllByStatus(EnrollmentStatus status, PageRequest request);

	Page<EnrollmentResponse> findAll(PageRequest pageRequest);
}
