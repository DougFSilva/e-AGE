package com.dougfsilva.e_AGE.application.usecases.responsible;

import java.util.List;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.responsible.ResponsibleRepository;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteResponsible {

	private final ResponsibleRepository repository;
	
	private final StudentRepository studentRepository;
	
	private final FindResponsible findResponsible;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		Responsible responsible = findResponsible.findByID(ID);
		List<Student> students = studentRepository.findAllByResponsible(responsible);
		students.forEach(student -> {
			student.setResponsible(null);
			studentRepository.save(student);
		});
		repository.delete(responsible);
		logger.deletedObjectLog(responsible);
	}
}
