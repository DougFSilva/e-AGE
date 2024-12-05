package com.dougfsilva.e_AGE.domain.enrollment;

import lombok.Getter;

@Getter
public enum EnrollmentStatus {

    ENROLLED("Student is currently enrolled"),
    DROPPED("Student has dropped the course"),
    COMPLETED("Student has completed the course");

    private final String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }
}