package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.dto.request.CreateStudentRequest;
import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.usecases.address.CreateAddress;
import com.dougfsilva.e_AGE.application.usecases.enterprise.FindEnterprise;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.person.PersonRepository;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateStudent {

	private final StudentRepository repository;
	
	private final PersonRepository personRepository;
	
	private final CreateAddress createAddress;
	
	private final FindEnterprise findEnterprise;
	
	private final StandardLogger logger;
	
	public StudentResponse execute(CreateStudentRequest request) {
		if(personRepository.findByRg(request.getRg()).isPresent()) {
			throw new DataIntegrityViolationException(String.format("Person with rg %S already registered in the system!", request.getRg()));
		}
		Address address = createAddress.execute(request.getAddress());
		Student student = new Student(
				request.getName(),
				request.getSex(),
				request.getRg(),
				request.getPhone(),
				request.getEmail(),
				request.getDateOfBirth(),
				address);
		if(request.getEnterpriseID() != null && !request.getEnterpriseID().isBlank()) {
			Enterprise enterprise = findEnterprise.findByID(request.getEnterpriseID());
			student.setEnterprise(enterprise);
		}
		Student createdStudent = repository.save(student);
		logger.createdObjectLog(createdStudent);
		return StudentResponse.fromStudent(createdStudent);
		
	}
}
