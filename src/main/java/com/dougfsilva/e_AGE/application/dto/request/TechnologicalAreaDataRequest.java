package com.dougfsilva.e_AGE.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record TechnologicalAreaDataRequest(String title, String description, MultipartFile image) {

}
