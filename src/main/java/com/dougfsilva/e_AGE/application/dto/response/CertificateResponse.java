package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.certificate.Certificate;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CertificateResponse {

	private String ID;
	private StudentResponse student;
	private CourseResponse course;
	private LocalDate certificationDate;
	
	public CertificateResponse(Certificate certificate) {
		this.ID = certificate.getID();
		this.student = StudentResponse.fromStudent(certificate.getStudent());
		this.course = CourseResponse.fromCourse(certificate.getCourse());
		this.certificationDate = certificate.getCertificationDate();
	}

	public static Page<CertificateResponse> fromPage(Page<Certificate> certificates) {
		return new Page<CertificateResponse>(
				certificates.getContent()
				.stream()
				.map(CertificateResponse::new)
				.collect(Collectors.toList()), 
				certificates.getNumber(), 
				certificates.getSize(),
				certificates.getTotalElements(),
				certificates.getTotalPages(), 
				certificates.getHasContent(), 
				certificates.getIsFirst(),
				certificates.getIsLast());
	}
	
	 public static CertificateResponse fromCertificate(Certificate certificate) {
	        return new CertificateResponse(certificate);
	    } 
}
