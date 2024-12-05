package com.dougfsilva.e_AGE.application.usecases.responsible;

import com.dougfsilva.e_AGE.application.dto.request.CreateResponsibleRequest;
import com.dougfsilva.e_AGE.application.dto.response.ResponsibleResponse;
import com.dougfsilva.e_AGE.application.usecases.address.CreateAddress;
import com.dougfsilva.e_AGE.application.usecases.student.FindStudent;
import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.person.PersonRepository;
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

	private final PersonRepository personRepository;

	private final CreateAddress createAddress;

	private final FindStudent findStudent;

	private final StandardLogger logger;

	public ResponsibleResponse execute(CreateResponsibleRequest request) {
		if (personRepository.findByRg(request.getRg()).isPresent()) {
			throw new DataIntegrityViolationException(
					String.format("Person with rg %S already registered in the system!", request.getRg()));
		}
		Student student = findStudent.findByID(request.getStudentID());
		Responsible responsible = new Responsible(request.getName(), request.getSex(), request.getRg(), request.getPhone(),
				request.getEmail(), request.getDateOfBirth());
		Address createdAddress = createAddress.execute(request.getAddress());
		responsible.setAddress(createdAddress);
		Responsible createdResponsible = repository.save(responsible);
		student.setResponsible(createdResponsible);
		studentRepository.save(student);
		logger.createdObjectLog(createdResponsible);
		return ResponsibleResponse.fromResponsible(createdResponsible);
	}

}
