package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateCertificateRequest {

	private String enrollmentID;
	private LocalDate certificationDate;
	
	public CreateCertificateRequest(String enrollmentID, LocalDate certificationDate) {
        if (enrollmentID == null || enrollmentID.isBlank()) {
            throw new IllegalArgumentException("Enrollment ID cannot be null or empty!");
        }
        if (certificationDate == null) {
            throw new IllegalArgumentException("Certification Date cannot be null!");
        }
        this.enrollmentID = enrollmentID;
        this.certificationDate = certificationDate;
    }
}
