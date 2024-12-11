package com.dougfsilva.e_AGE.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReenrollRequest {
	
	private String enrollmentID;
    private String clazzID;
    private LocalDate date;

    public ReenrollRequest(String enrollmentID, String clazzID, LocalDate date) {
        if (enrollmentID == null || enrollmentID.isBlank()) {
            throw new IllegalArgumentException("Enrollment ID cannot be null or empty!");
        }
        if (clazzID == null || clazzID.isBlank()) {
            throw new IllegalArgumentException("Class ID cannot be null or empty!");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null!");
        }

        this.enrollmentID = enrollmentID;
        this.clazzID = clazzID;
        this.date = date;
    }
}