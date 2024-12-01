package com.dougfsilva.e_AGE.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record CreateTechnologicalAreaRequest(String tilte, String description, MultipartFile image) {

}
