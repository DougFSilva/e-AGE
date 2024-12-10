package com.dougfsilva.e_AGE.application.dto.response;

import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class StudentResponse extends PersonResponse{
	
	private Responsible responsible;
	private Enterprise enterprise;

	public StudentResponse(Student student) {
		super(
				student.getID(), 
				student.getUser(), 
				student.getName(), 
				student.getSex(),
				student.getRg(), 
				student.getPhone(), 
				student.getEmail().getAddress(), 
				student.getDateOfBirth(), 
				student.getAddress());
		this.responsible = student.getResponsible();
		this.enterprise = student.getEnterprise();
	}

	
	public static Page<StudentResponse> fromPage(Page<Student> students) {
		return new Page<StudentResponse>(
				students.getContent()
				.stream()
				.map(StudentResponse::new)
				.collect(Collectors.toList()), 
				students.getNumber(), 
				students.getSize(),
				students.getTotalElements(),
				students.getTotalPages(), 
				students.getHasContent(), 
				students.getIsFirst(),
				students.getIsLast());
	}
	
	public static StudentResponse fromStudent(Student student) {
		return new StudentResponse(student);
	}

}
