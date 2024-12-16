package com.dougfsilva.e_AGE.application.usecases.employee;

import org.springframework.web.multipart.MultipartFile;

import com.dougfsilva.e_AGE.application.dto.response.EmployeeResponse;
import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeImageUploader {

	private final EmployeeRepository repository;
	private final ImageStorageService imageService;
	private final StandardLogger logger;

	public EmployeeResponse upload(String ID, MultipartFile image) {
		try {
			validateImage(image);
			Employee employee = repository.findByIdOrThrow(ID);
			String imageUrl = imageService.uploadImage(image, ImageType.EMPLOYEE, ImageNameGenerator.byEmployee(employee));
			employee.setImage(imageUrl);
			Employee savedEmployee = repository.save(employee);
			logger.info(String.format("Image uploaded successfully for employee ID %s, %s ", savedEmployee.getID(), savedEmployee.getName()));
			return EmployeeResponse.fromEmployee(savedEmployee);
		} catch (ObjectNotFoundException | IllegalArgumentException | ImageOperationException e) {
			String message = String.format("Error while uploading employee image ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new EmployeeOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when uploading employee image ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new EmployeeOperationException(message, e);
		}
	}

		

	private void validateImage(MultipartFile image) {
		if (image == null || image.isEmpty()) {
			throw new IllegalArgumentException("Image cannot be null or Empty");
		}
	}
}
