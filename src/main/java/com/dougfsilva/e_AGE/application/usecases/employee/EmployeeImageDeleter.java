package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.ImageOperationException;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeImageDeleter {

	private final EmployeeRepository repository;
	private final EmployeeFinder employeeFinder;
	private final ImageStorageService  imageService;
	private final StandardLogger logger;

	public void deleteByID(String ID) {
		try {
			Employee employee = employeeFinder.findByID(ID);
			imageService.deleteImage(ImageType.EMPLOYEE, ImageNameGenerator.byEmployee(employee));
			employee.setImage(null);
			repository.save(employee);
			logger.info(String.format("Image deleted successfully for employee ID %s - %s ", employee.getID(), employee.getName()));
		} catch (Exception e) {
			logger.error("Unexpected error when deleting employee image: " + e.getMessage());
			throw new ImageOperationException("Error while delete employee image", e);
		}
	}
}
