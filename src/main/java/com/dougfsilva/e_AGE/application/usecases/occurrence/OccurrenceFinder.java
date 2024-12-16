package com.dougfsilva.e_AGE.application.usecases.occurrence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.application.dto.response.OccurrenceResponse;
import com.dougfsilva.e_AGE.domain.clazz.Clazz;
import com.dougfsilva.e_AGE.domain.employee.Employee;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceRepository;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceType;
import com.dougfsilva.e_AGE.domain.student.Student;
import com.dougfsilva.e_AGE.domain.user.ProfileType;
import com.dougfsilva.e_AGE.domain.utilities.UserContext;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;
import com.dougfsilva.e_AGE.domain.utilities.pagination.PageRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OccurrenceFinder {

	private final OccurrenceRepository repository;
	private final UserContext userContext;

	public OccurrenceResponse findByID(String ID) {
		return OccurrenceResponse.fromOccurrence(repository.findByIdOrThrow(ID));
	}

	Page<OccurrenceResponse> findAllByOpeningDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest) {
		return isManagement()
				? OccurrenceResponse.fromPage(repository.findAllByOpeningDatePeriod(min, max, pageRequest))
				: OccurrenceResponse.fromPage(
						repository.findAllByOpeningDatePeriodAndReporterUserOrOpeningDatePeriodAndRestricted(min, max,
								userContext.getCurrentUserOrThrow(), false, pageRequest));
	}

	Page<OccurrenceResponse> findAllByClosingDatePeriod(LocalDate min, LocalDate max, PageRequest pageRequest) {
		return isManagement()
				? OccurrenceResponse.fromPage(repository.findAllByClosingDatePeriod(min, max, pageRequest))
				: OccurrenceResponse.fromPage(
						repository.findAllByClosingDatePeriodAndReporterUserOrClosingDatePeriodAndRestricted(min, max,
								userContext.getCurrentUserOrThrow(), false, pageRequest));
	}

	Page<OccurrenceResponse> findAllByReporter(Employee reporter, PageRequest pageRequest) {
		return isManagement() ? OccurrenceResponse.fromPage(repository.findAllByReporter(reporter, pageRequest))
				: OccurrenceResponse.fromPage(repository
						.findAllByReporterAndReporterUserOrReporterAndRestricted(reporter, false, pageRequest));
	}

	List<OccurrenceResponse> findAllByStudant(Student studant, PageRequest pageRequest) {
		return isManagement()
				? repository.findAllByStudant(studant).stream().map(OccurrenceResponse::new)
						.collect(Collectors.toList())
				: repository
						.findAllByStudantAndReporterUserOrStudentAndRestricted(studant,
								userContext.getCurrentUserOrThrow(), false)
						.stream().map(OccurrenceResponse::new).collect(Collectors.toList());
	}

	Page<OccurrenceResponse> findAllByClazz(Clazz clazz, PageRequest pageRequest) {
		return isManagement() ? OccurrenceResponse.fromPage(repository.findAllByClazz(clazz, pageRequest))
				: OccurrenceResponse.fromPage(repository.findAllByClazzAndReporterUserOrClazzAndRestricted(clazz,
						userContext.getCurrentUserOrThrow(), false, pageRequest));
	}

	Page<OccurrenceResponse> findAllByOccurrenceType(OccurrenceType occurrenceType, PageRequest pageRequest) {
		return isManagement()
				? OccurrenceResponse.fromPage(repository.findAllByOccurrenceType(occurrenceType, pageRequest))
				: OccurrenceResponse.fromPage(
						repository.findAllByOccurrenceTypeAndReporterUserOrOccurrenceTypeAndRestricted(occurrenceType,
								userContext.getCurrentUserOrThrow(), false, pageRequest));
	}

	Page<OccurrenceResponse> findAllByForwarding(Boolean forwarding, PageRequest pageRequest) {
		return isManagement() ? OccurrenceResponse.fromPage(repository.findAllByForwarding(forwarding, pageRequest))
				: OccurrenceResponse.fromPage(repository.findAllByForwardingAndReporterUserOrForwardingAndRestricted(
						forwarding, false, userContext.getCurrentUserOrThrow(), pageRequest));
	}

	Page<OccurrenceResponse> findAllByOpen(Boolean open, PageRequest pageRequest) {
		return isManagement() ? OccurrenceResponse.fromPage(repository.findAllByOpen(open, pageRequest))
				: OccurrenceResponse.fromPage(repository.findAllByOpenAndReporterUserOrOpenAndRestricted(open,
						userContext.getCurrentUserOrThrow(), false, pageRequest));
	}

	Page<OccurrenceResponse> findAll(PageRequest pageRequest) {
		return isManagement() ? OccurrenceResponse.fromPage(repository.findAll(pageRequest))
				: OccurrenceResponse.fromPage(repository.findAllAndReporterUserOrRestricted(false,
						userContext.getCurrentUserOrThrow(), pageRequest));
	}

	private Boolean isManagement() {
		return userContext.getCurrentUserOrThrow().checkContainsProfile(ProfileType.MANAGEMENT);
	}

}
