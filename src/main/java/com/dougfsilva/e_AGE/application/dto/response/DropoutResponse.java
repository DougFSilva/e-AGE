package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.dropout.Dropout;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DropoutResponse {

	private String ID;
	private StudentResponse student;
	private ClazzResponse clazz;
	private String reason;
	private LocalDate date;
	
	public DropoutResponse(Dropout dropout) {
		this.ID = dropout.getID();
		this.student = StudentResponse.fromStudent(dropout.getStudent());
		this.clazz = ClazzResponse.fromClazz(dropout.getClazz());
		this.reason = dropout.getReason();
		this.date = dropout.getDate();
	}

	public static Page<DropoutResponse> fromPage(Page<Dropout> dropouts) {
		return new Page<DropoutResponse>(
				dropouts.getContent()
				.stream()
				.map(DropoutResponse::new)
				.collect(Collectors.toList()), 
				dropouts.getNumber(), 
				dropouts.getSize(),
				dropouts.getTotalElements(),
				dropouts.getTotalPages(), 
				dropouts.getHasContent(), 
				dropouts.getIsFirst(),
				dropouts.getIsLast());
	}
	
	 public static DropoutResponse fromDropout(Dropout dropout) {
	        return new DropoutResponse(dropout);
	    }

}
