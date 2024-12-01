package com.dougfsilva.e_AGE.application.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.dougfsilva.e_AGE.domain.address.Address;
import com.dougfsilva.e_AGE.domain.guardian.Guardian;
import com.dougfsilva.e_AGE.domain.utilities.pagination.Page;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"ID"})
public class GuardianResponse {
	
	private String ID;

	private UserResponse user;

	private String name;

	private String rg;

	private String phone;

	private String email;

	private LocalDate dateOfBirth;

	private Address address;
	
	public GuardianResponse(Guardian guardian) {
		this.ID = guardian.getID();
		this.user = guardian.getUser() != null? UserResponse.fromUser(guardian.getUser()) : null;
		this.name = guardian.getName();
		this.rg = guardian.getRg();
		this.phone = guardian.getPhone();
		this.email = guardian.getEmail().getAddress();
		this.dateOfBirth = guardian.getDateOfBirth();
		this.address = guardian.getAddress();
	}
	
	
	
	public static Page<GuardianResponse> fromPage(Page<Guardian> guardians) {
		return new Page<GuardianResponse>(
				guardians.getContent()
				.stream()
				.map(GuardianResponse::new)
				.collect(Collectors.toList()), 
				guardians.getNumber(), 
				guardians.getSize(),
				guardians.getTotalElements(),
				guardians.getTotalPages(), 
				guardians.getHasContent(), 
				guardians.getIsFirst(),
				guardians.getIsLast());
	}
	
	public static GuardianResponse fromGuardian(Guardian guardian) {
		return new GuardianResponse(guardian);
	}


}
