package com.dougfsilva.e_AGE.application.usecases.employee;

import com.dougfsilva.e_AGE.application.usecases.utilities.ImageNameGenerator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.AddressRepository;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.employee.EmployeeRepository;
import com.dougfsilva.e_AGE.domain.exception.EmployeeOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.user.UserRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeDeleter {

	private final EmployeeRepository repository;
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;
	private final ImageStorageService imageService;
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			Employee employee = repository.findByIdOrThrow(ID);
			addressRepository.delete(employee.getAddress());
			if(employee.getUser() != null) {
				userRepository.delete(employee.getUser());
			}
			imageService.deleteImage(ImageType.EMPLOYEE, ImageNameGenerator.byEmployee(employee));
			repository.delete(employee);
			logger.info(String.format("Deleted employee ID %s, %s", employee.getID(), employee.getName()));
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while deleting employee ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new EmployeeOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting employee ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new EmployeeOperationException(message, e);
		}
	}
}
