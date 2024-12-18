package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceType;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceSignature;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceStatus;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OccurrenceResponse {

	private String ID;
	private LocalDateTime openingDate;
	private LocalDateTime finishingDate;
	private EmployeeResponse reporter;
	private StudentResponse studant;
	private ClazzResponse clazz;
	private String curricularUnit;
	private OccurrenceType occurrenceType;
	private Boolean forwarded;
	private Boolean restricted;
	private String description;
	private String treatmentDescription;
	private OccurrenceStatus status;
	private OccurrenceSignature studentSignature;
	private EmployeeResponse responsibleForFinishing;
	
	public OccurrenceResponse(Occurrence occurrence) {
		this.ID = occurrence.getID();
		this.openingDate = occurrence.getOpeningDate();
		this.finishingDate = occurrence.getFinishingDate();
		this.reporter = EmployeeResponse.fromEmployee(occurrence.getReporter());
		this.studant = StudentResponse.fromStudent(occurrence.getStudent());
		this.clazz = ClazzResponse.fromClazz(occurrence.getClazz());
		this.curricularUnit = occurrence.getCurricularUnit();
		this.occurrenceType = occurrence.getOccurrenceType();
		this.forwarded = occurrence.getForwarded();
		this.restricted = occurrence.getRestricted();
		this.description = occurrence.getDescription();
		this.treatmentDescription = occurrence.getTreatmentDescription();
		this.status = occurrence.getStatus();
		this.studentSignature = occurrence.getStudentSignature();
		this.responsibleForFinishing = EmployeeResponse.fromEmployee(occurrence.getResponsibleForFinishing());
	}


	public static Page<OccurrenceResponse> fromPage(Page<Occurrence> occurrences) {
		return new Page<OccurrenceResponse>(
				occurrences.getContent()
				.stream()
				.map(OccurrenceResponse::new)
				.collect(Collectors.toList()), 
				occurrences.getNumber(), 
				occurrences.getSize(),
				occurrences.getTotalElements(),
				occurrences.getTotalPages(), 
				occurrences.getHasContent(), 
				occurrences.getIsFirst(),
				occurrences.getIsLast());
	}
	
	 public static OccurrenceResponse fromOccurrence(Occurrence occurrence) {
	        return new OccurrenceResponse(occurrence);
	    } 
}
