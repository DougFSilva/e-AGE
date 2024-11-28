package com.dougfsilva.e_AGE.application.dto.request;

import com.dougfsilva.e_AGE.domain.course.CourseModality;

public record UpdateCourseRequest(String ID, CourseModality modality, String title, String technologicalAreaID) {

}
