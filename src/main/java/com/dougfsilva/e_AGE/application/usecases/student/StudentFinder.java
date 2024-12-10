package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.application.usecases.clazz.ClazzFinder;
import com.dougfsilva.e_AGE.application.usecases.enterprise.EnterpriseFinder;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentFinder {

	private final StudentRepository repository;
	private final ClazzFinder clazzFinder;
	private final EnterpriseFinder enterpriseFinder;
	
	public Student findByID(String ID) {
		return repository.findByID(ID).orElseThrow(() -> new ObjectNotFoundException(String.format("Student with ID %s not Found!", ID)));
	}

	public StudentResponse findByIDAsStudentResponse(String ID) {
		return StudentResponse.fromStudent(findByID(ID));
	}
	
	public Page<StudentResponse> findAllByClazz(String clazzID, PageRequest pageRequest){
		Clazz clazz = clazzFinder.findByID(clazzID);
		return StudentResponse.fromPage(repository.findAllByClazz(clazz, pageRequest));
	}
	
	public Page<Student> findAllByEnterprise(String enterpriseID, PageRequest pageRequest){
		Enterprise enterprise = enterpriseFinder.findByID(enterpriseID);
		return repository.findAllByEnterprise(enterprise, pageRequest);
	}
	
	public Page<Student> findAll(PageRequest pageRequest){
		return repository.findAll(pageRequest);
	}
}
