package com.dougfsilva.e_AGE.application.usecases.certificate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.application.dto.response.CertificateResponse;
import com.dougfsilva.e_AGE.domain.certificate.CertificateRepository;
import com.dougfsilva.e_AGE.domain.course.Course;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CertificateFinder {

	private final CertificateRepository repository;

	public CertificateResponse findByID(String ID) {
		return CertificateResponse.fromCertificate(repository.findByIdOrThrow(ID));
	}

	List<CertificateResponse> findAllByStudent(Student student) {
		return repository.findAllByStudent(student).stream().map(CertificateResponse::new).collect(Collectors.toList());
	}

	Page<CertificateResponse> findAllByCourse(Course course, PageRequest pageRequest) {
		return CertificateResponse.fromPage(repository.findAllByCourse(course, pageRequest));
	}

	Page<CertificateResponse> findAllByDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest) {
		return CertificateResponse.fromPage(repository.findAllByDatePeriod(min, max, pageRequest));
	}

	Page<CertificateResponse> findAll(PageRequest pageRequest) {
		return CertificateResponse.fromPage(repository.findAll(pageRequest));
	}
}
