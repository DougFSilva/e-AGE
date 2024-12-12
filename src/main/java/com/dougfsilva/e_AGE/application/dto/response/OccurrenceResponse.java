package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceType;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OccurrenceResponse {

	private String ID;
	private LocalDateTime openingDate;
	private LocalDateTime closingDate;
	private EmployeeResponse reporter;
	private StudentResponse studant;
	private ClazzResponse clazz;
	private String curricularUnit;
	private OccurrenceType occurrenceType;
	private Boolean restricted;
	private Boolean forwarding;
	private String description;
	private String resolutionDescription;
	private Boolean open;
	private String studantSignature;
	private EmployeeResponse closureResponsible;
	
	public OccurrenceResponse(Occurrence occurrence) {
		this.ID = occurrence.getID();
		this.openingDate = occurrence.getOpeningDate();
		this.closingDate = occurrence.getClosingDate();
		this.reporter = EmployeeResponse.fromEmployee(occurrence.getReporter());
		this.studant = StudentResponse.fromStudent(occurrence.getStudant());
		this.clazz = ClazzResponse.fromClazz(occurrence.getClazz());
		this.curricularUnit = occurrence.getCurricularUnit();
		this.occurrenceType = occurrence.getOccurrenceType();
		this.restricted = occurrence.getRestricted();
		this.forwarding = occurrence.getForwarding();
		this.description = occurrence.getDescription();
		this.resolutionDescription = occurrence.getResolutionDescription();
		this.open = occurrence.getOpen();
		this.studantSignature = occurrence.getStudantSignature();
		this.closureResponsible = EmployeeResponse.fromEmployee(occurrence.getClosureResponsible());
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
