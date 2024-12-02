package com.dougfsilva.e_AGE.application.usecases.student;

import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.usecases.clazz.FindClazz;
import com.dougfsilva.e_AGE.application.usecases.enterprise.FindEnterprise;
import com.dougfsilva.e_AGE.application.usecases.responsible.FindResponsible;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindStudent {

	private final StudentRepository repository;
	
	private final FindClazz findClazz;
	
	private final FindEnterprise findEnterprise;
	
	private final FindResponsible findResponsible;

	public Student findByID(String ID) {
		return repository.findByID(ID).orElseThrow(() -> new ObjectNotFoundException(String.format("Student with ID %S not Found!", ID)));
	}

	public StudentResponse findByIDAsStudentResponse(String ID) {
		return StudentResponse.fromStudent(findByID(ID));
	}
	
	public Page<StudentResponse> findAllByClazz(String clazzID, PageRequest pageRequest){
		Clazz clazz = findClazz.findByID(clazzID);
		return StudentResponse.fromPage(repository.findAllByClazz(clazz, pageRequest));
	}
	
	public List<StudentResponse> findAllByResposible(String responsibleID, PageRequest pageRequest){
		Responsible responsible = findResponsible.findByID(responsibleID);
		return repository.findAllByResponsible(responsible).stream().map(StudentResponse::new).collect(Collectors.toList());
	}
	
	public Page<Student> findAllByEnterprise(String enterpriseID, PageRequest pageRequest){
		Enterprise enterprise = findEnterprise.findByID(enterpriseID);
		return repository.findAllByEnterprise(enterprise, pageRequest);
	}
	
	public Page<Student> findAll(PageRequest pageRequest){
		return repository.findAll(pageRequest);
	}
}
