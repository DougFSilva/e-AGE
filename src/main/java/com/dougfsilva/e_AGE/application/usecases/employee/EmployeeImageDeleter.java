package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;
import com.dougfsilva.e_AGE.domain.exception.EmployeeValidatorException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
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
			deleteImage(employee);
			logger.info(String.format("Image deleted successfully for employee ID %s - %s", employee.getID(), employee.getName()));
		} catch (ObjectNotFoundException | EmployeeValidatorException e) {
			String message = String.format("Error while delete image of employee ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new EmployeeOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting employee image ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new EmployeeOperationException(message, e);
		}
	}
	
	private void deleteImage(Employee employee) {
		imageService.deleteImage(ImageType.EMPLOYEE, ImageNameGenerator.byEmployee(employee));
		employee.setImage(null);
		repository.save(employee);
	}
}
