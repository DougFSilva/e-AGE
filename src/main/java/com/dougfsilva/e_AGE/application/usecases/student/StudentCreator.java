package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.dto.request.CreateStudentRequest;
import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.usecases.address.AddressCreator;
import com.dougfsilva.e_AGE.application.usecases.person.PersonValidator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.PersonValidationException;
import com.dougfsilva.e_AGE.domain.exception.StudentOperationException;
import com.dougfsilva.e_AGE.domain.exception.UserOperationException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentCreator {

	private final StudentRepository repository;
	private final EnterpriseRepository enterpriseRepository;
	private final AddressCreator addressCreator;
	private final ImageStorageService imageService;
	private final StudentUserCreator studentUserCreator;
	private final PersonValidator personValidator;
	private final StandardLogger logger;
	
	public StudentResponse create(CreateStudentRequest request) {
		
		try {
			personValidator.uniqueRg(request.getRg());
			Address address = addressCreator.create(request.getAddress());
			Student student = new Student(
					request.getName(),
					request.getSex(),
					request.getRg(),
					request.getPhone(),
					request.getEmail(),
					request.getDateOfBirth(),
					address,
					imageService.getDefaultImage(ImageType.STUDENT),
					request.getResponsible());
			if(request.getEnterpriseID() != null && !request.getEnterpriseID().isBlank()) {
				Enterprise enterprise = enterpriseRepository.findByIdOrThrow(request.getEnterpriseID());
				student.setEnterprise(enterprise);
			}
			Student createdStudent = repository.save(student);
			createUser(request, createdStudent);
			logger.info(String.format("Created student ID %s, %s", createdStudent.getID(), createdStudent.getName()));
			return StudentResponse.fromStudent(createdStudent);
		} catch (ObjectNotFoundException | PersonValidationException | UserOperationException e) {
			String message = String.format("Error while creating student %s : %s", request.getName(), e.getMessage());
			logger.warn(message, e);
			throw new StudentOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when creating student %s : %s", request.getName(), e.getMessage());
			logger.error(message, e);
			throw new StudentOperationException(message, e);
		}
	}
	
	private void createUser(CreateStudentRequest request, Student student) {
		if(request.getCreateDefaultUser() != null && request.getCreateDefaultUser()) {
			studentUserCreator.createByID(student.getID());
		}
	}
	
}
