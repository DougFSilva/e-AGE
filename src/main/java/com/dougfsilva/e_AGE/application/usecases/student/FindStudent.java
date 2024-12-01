package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindStudent {

	private final StudentRepository repository;

	public Student findByID(String ID) {
		return repository.findByID(ID).orElseThrow(() -> new ObjectNotFoundException(String.format("Student with ID %S not Found!", ID)));
	}

	public StudentResponse findByIDAsStudentResponse(String ID) {
		return StudentResponse.fromStudent(findByID(ID));
	}
}
