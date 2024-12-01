package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.enterprise.Enterprise;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentStatus;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = {"ID"})
@ToString
public class StudentResponse {
	
	private String ID;

	private UserResponse user;

	private String name;

	private String rg;

	private String phone;

	private String email;

	private LocalDate dateOfBirth;

	private Address address;

	private String enrollment;

	private List<ClazzResponse> clazzes;

	private GuardianResponse guardian;

	private Enterprise enterprise;

	private StudentStatus status;

	public StudentResponse(Student student) {
		this.ID = student.getID();
		this.user = student.getUser() != null ? UserResponse.fromUser(student.getUser()) : null;
		this.name = student.getName();
		this.rg = student.getRg();
		this.phone = student.getPhone();
		this.email = student.getEmail().getAddress();
		this.dateOfBirth = student.getDateOfBirth();
		this.address = student.getAddress();
		this.enrollment = student.getEnrollment();
		this.clazzes = student.getClazzes().stream().map(ClazzResponse::new).collect(Collectors.toList());
		this.guardian = GuardianResponse.fromGuardian(student.getGuardian());
		this.enterprise = student.getEnterprise();
		this.status = student.getStatus();
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
