package com.dougfsilva.e_AGE.application.dto.response;

import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ResponsibleResponse extends PersonResponse {

	public ResponsibleResponse(Responsible responsible) {
		super(
				responsible.getID(), 
				responsible.getUser(), 
				responsible.getName(), 
				responsible.getSex(),
				responsible.getRg(),
				responsible.getPhone(), 
				responsible.getEmail().getAddress(), 
				responsible.getDateOfBirth(),
				responsible.getAddress());
	}

	public static Page<ResponsibleResponse> fromPage(Page<Responsible> responsibles) {
		return new Page<ResponsibleResponse>(
				responsibles.getContent()
				.stream()
				.map(ResponsibleResponse::new)
				.collect(Collectors.toList()),
				responsibles.getNumber(),
				responsibles.getSize(), 
				responsibles.getTotalElements(),
				responsibles.getTotalPages(), 
				responsibles.getHasContent(), 
				responsibles.getIsFirst(),
				responsibles.getIsLast());
	}

	public static ResponsibleResponse fromResponsible(Responsible responsible) {
		return new ResponsibleResponse(responsible);
	}

}
