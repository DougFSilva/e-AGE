package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.dto.request.ResponsibleDataRequest;
import com.dougfsilva.e_AGE.application.dto.response.ResponsibleResponse;
import com.dougfsilva.e_AGE.application.usecases.Address.CreateAddress;
import com.dougfsilva.e_AGE.application.usecases.student.FindStudent;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.person.Email;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateResponsible {

	private final ResponsibleRepository repository;
	
	private final StudentRepository studentRepository;
	
	private final CreateAddress createAddress;
	
	private final FindStudent findStudent;
	
	private final StandardLogger logger;

	public ResponsibleResponse execute(ResponsibleDataRequest request) {
		if(request.studentID() == null || request.studentID().isBlank()) {
			throw new DataIntegrityViolationException("It is not possible to create a responsible person without a student");
		}
		if(repository.findByRg(request.rg()).isPresent()) {
			throw new DataIntegrityViolationException(String.format("Responsible with rg %S already registered in the system", request.rg()));
		}
		Student student = findStudent.findByID(request.studentID());
		Responsible responsible = new Responsible(request.name(), request.rg(), request.phone(), new Email(request.email()),request.dateOfBirth());
		if(request.sameResidence()) {
			responsible.setAddress(student.getAddress());
		}else {
			Address createdAddress = createAddress.execute(request.addressDataRequest());
			responsible.setAddress(createdAddress);
		}	
		Responsible createdResponsible = repository.save(responsible);
		student.setResponsible(createdResponsible);
		studentRepository.save(student);
		logger.createdObjectLog(createdResponsible);
		return ResponsibleResponse.fromResponsible(createdResponsible);
	}

}
