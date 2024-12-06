package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.enrollment.Enrollment;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.Getter;

@Getter
public class EnrollmentResponse {

	private String ID;
	
	private String registration;
	
	private StudentResponse student;
	
	private ClazzResponse clazz;
	
	private LocalDate date;
	
	private EnrollmentStatus status;
	
	public EnrollmentResponse(Enrollment enrollment) {
		this.ID = enrollment.getID();
		this.registration = enrollment.getRegistration();
		this.student = StudentResponse.fromStudent(enrollment.getStudent());
		this.clazz = ClazzResponse.fromClazz(enrollment.getClazz());
		this.date = enrollment.getDate();
	}

	public static Page<EnrollmentResponse> fromPage(Page<Enrollment> enrollment) {
		return new Page<EnrollmentResponse>(
				enrollment.getContent()
				.stream()
				.map(EnrollmentResponse::new)
				.collect(Collectors.toList()), 
				enrollment.getNumber(), 
				enrollment.getSize(),
				enrollment.getTotalElements(),
				enrollment.getTotalPages(), 
				enrollment.getHasContent(), 
				enrollment.getIsFirst(),
				enrollment.getIsLast());
	}
	
	 public static EnrollmentResponse fromEnrollment(Enrollment enrollment) {
	        return new EnrollmentResponse(enrollment);
	    }

}
