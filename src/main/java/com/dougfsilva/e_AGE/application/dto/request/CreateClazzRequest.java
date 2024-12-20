package com.dougfsilva.e_AGE.application.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateClazzRequest {

	private Integer number;
    private String code;
    private String courseID;

    public CreateClazzRequest(Integer number, String code, String courseID) {
        if (number == null || number <= 0) {
            throw new IllegalArgumentException("Number must be a positive integer and cannot be null!");
        }
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Code cannot be null or empty!");
        }
        if (courseID == null || courseID.trim().isBlank()) {
            throw new IllegalArgumentException("Course ID cannot be null or empty!");
        }

        this.number = number;
        this.code = code;
        this.courseID = courseID;
    }

}
