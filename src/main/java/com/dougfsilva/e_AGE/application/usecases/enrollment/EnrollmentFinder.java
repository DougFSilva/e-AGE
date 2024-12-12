package com.dougfsilva.e_AGE.application.usecases.enrollment;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.application.dto.response.EnrollmentResponse;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.clazz.ClazzRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.course.CourseRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentRepository;
import com.dougfsilva.e_AGE.domain.enrollment.EnrollmentStatus;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.student.StudentRepository;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnrollmentFinder {

	private final EnrollmentRepository repository;
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	private final ClazzRepository clazzRepository;

	public EnrollmentResponse findByID(String ID) {
		return EnrollmentResponse.fromEnrollment(repository.findByIdOrThrow(ID));
	}

	List<EnrollmentResponse> findAllByStudent(String studentID) {
		Student student = studentRepository.findByIdOrThrow(studentID);
		return repository.findAllByStudent(student).stream().map(EnrollmentResponse::new).collect(Collectors.toList());
	}

	List<EnrollmentResponse> findAllByClazz(String clazzID) {
		Clazz clazz = clazzRepository.findByIdOrThrow(clazzID);
		return repository.findAllByClazz(clazz).stream().map(EnrollmentResponse::new).collect(Collectors.toList());
	}

	Page<EnrollmentResponse> findAllByCourse(String courseID, PageRequest pageRequest) {
		Course course = courseRepository.findByIdOrThrow(courseID);
		return EnrollmentResponse.fromPage(repository.findAllByCourse(course));
	}
	
	Page<EnrollmentResponse> findAllByDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest) {
		return EnrollmentResponse.fromPage(repository.findAllByDatePeriod(min, max, pageRequest));
	}

	Page<EnrollmentResponse> findAllByStatus(EnrollmentStatus status, PageRequest request) {
		return EnrollmentResponse.fromPage(repository.findAllByStatus(status, request));
	}

	Page<EnrollmentResponse> findAll(PageRequest pageRequest) {
		return EnrollmentResponse.fromPage(repository.findAll(pageRequest));
	}
}
