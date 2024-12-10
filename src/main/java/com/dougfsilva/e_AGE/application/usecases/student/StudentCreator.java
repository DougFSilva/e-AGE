package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.dto.request.CreateStudentRequest;
import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.usecases.address.AddressCreator;
import com.dougfsilva.e_AGE.application.usecases.enterprise.EnterpriseFinder;
import com.dougfsilva.e_AGE.application.usecases.person.PersonValidator;
import com.dougfsilva.e_AGE.application.usecases.user.UserCreator;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.exception.StudentOperationException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.user.User;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageStorageService;
import com.dougfsilva.e_AGE.domain.utilities.image.ImageType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentCreator {

	private final StudentRepository repository;
	private final AddressCreator addressCreator;
	private final EnterpriseFinder enterpriseFinder;
	private final ImageStorageService imageService;
	private final UserCreator userCreator;
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
				Enterprise enterprise = enterpriseFinder.findByID(request.getEnterpriseID());
				student.setEnterprise(enterprise);
			}
			Student createdStudent = repository.save(student);
			if(request.getCreateDefaultUser() != null && request.getCreateDefaultUser()) {
				User user = userCreator.create(createdStudent);
				createdStudent.setUser(user);
				repository.save(createdStudent);
			}
			logger.info(String.format("Created Student ID %s - %s", createdStudent.getID(), createdStudent.getName()));
			return StudentResponse.fromStudent(createdStudent);
		} catch (Exception e) {
			logger.error("Unexpected error when creating student: " + e.getMessage());
			throw new StudentOperationException("Error while create student", e);
		}
	}

}
