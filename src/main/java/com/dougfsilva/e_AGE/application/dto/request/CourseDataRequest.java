package com.dougfsilva.e_AGE.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.domain.course.CourseModality;

public record CourseDataRequest(CourseModality modality, String title, String technologicalAreaID, MultipartFile image) {

}
