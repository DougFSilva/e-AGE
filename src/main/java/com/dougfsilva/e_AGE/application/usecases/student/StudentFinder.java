package com.dougfsilva.e_AGE.application.usecases.student;

import com.dougfsilva.e_AGE.application.dto.response.StudentResponse;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.enterprise.EnterpriseRepository;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentFinder {

	private final StudentRepository repository;
	private final ClazzRepository clazzRepository;
	private final EnterpriseRepository enterpriseRepository;
	
	public StudentResponse findByIDA(String ID) {
		return StudentResponse.fromStudent(repository.findByIdOrThrow(ID));
	}
	
	public Page<StudentResponse> findAllByClazz(String clazzID, PageRequest pageRequest){
		Clazz clazz = clazzRepository.findByIdOrThrow(clazzID);
		return StudentResponse.fromPage(repository.findAllByClazz(clazz, pageRequest));
	}
	
	public Page<Student> findAllByEnterprise(String enterpriseID, PageRequest pageRequest){
		Enterprise enterprise = enterpriseRepository.findByIdOrThrow(enterpriseID);
		return repository.findAllByEnterprise(enterprise, pageRequest);
	}
	
	public Page<Student> findAll(PageRequest pageRequest){
		return repository.findAll(pageRequest);
	}
}
