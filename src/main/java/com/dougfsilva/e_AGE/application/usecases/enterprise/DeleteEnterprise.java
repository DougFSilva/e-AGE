package com.dougfsilva.e_AGE.application.usecases.enterprise;

import java.util.List;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteEnterprise {

	private final EnterpriseRepository repository;
	
	private final StudentRepository studentRepository;
	
	private final FindEnterprise findEnterprise;
	
	private final StandardLogger logger;
	
	public void execute(String ID) {
		Enterprise enterprise = findEnterprise.findByID(ID);
		List<Student> studentsByEnterprise = studentRepository.findAllByEnterprise(enterprise);
		studentsByEnterprise.forEach(student -> {
			student.setEnterprise(null);
		});
		studentRepository.saveAll(studentsByEnterprise);
		repository.delete(enterprise);
		logger.deletedObjectLog(enterprise);
	}
}
