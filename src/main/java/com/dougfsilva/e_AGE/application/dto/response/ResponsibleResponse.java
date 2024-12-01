package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.responsible.Responsible;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"ID"})
public class ResponsibleResponse {
	
	private String ID;

	private UserResponse user;

	private String name;

	private String rg;

	private String phone;

	private String email;

	private LocalDate dateOfBirth;

	private Address address;
	
	public ResponsibleResponse(Responsible responsible) {
		this.ID = responsible.getID();
		this.user = responsible.getUser() != null? UserResponse.fromUser(responsible.getUser()) : null;
		this.name = responsible.getName();
		this.rg = responsible.getRg();
		this.phone = responsible.getPhone();
		this.email = responsible.getEmail().getAddress();
		this.dateOfBirth = responsible.getDateOfBirth();
		this.address = responsible.getAddress();
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
