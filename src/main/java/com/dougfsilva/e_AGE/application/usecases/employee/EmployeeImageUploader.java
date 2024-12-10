package com.dougfsilva.e_AGE.application.usecases.employee;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeImageUploader {

	private final EmployeeRepository repository;
	private final EmployeeFinder employeeFinder;
	private final ImageStorageService imageService;
	private final StandardLogger logger;

	public EmployeeResponse upload(String ID, MultipartFile image) {
		try {
			if (image == null || image.isEmpty()) {
				throw new IllegalArgumentException("Image cannot be null or Empty");
			}
			Employee employee = employeeFinder.findByID(ID);
			String imageUrl = imageService.uploadImage(image, ImageType.EMPLOYEE, ImageNameGenerator.byEmployee(employee));
			employee.setImage(imageUrl);
			Employee updatedEmployee = repository.save(employee);
	        logger.info(String.format("Image uploaded successfully for employee ID %s - %s ", employee.getID(), employee.getName()));
			return EmployeeResponse.fromEmployee(updatedEmployee);
		} catch (Exception e) {
			logger.error("Unexpected error when uploading employee image: " + e.getMessage());
			throw new ImageOperationException("Error while upload employee image", e);
		}
	}
}
