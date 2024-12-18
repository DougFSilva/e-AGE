package com.dougfsilva.e_AGE.domain.occurrence;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OccurrenceSignature {

	private String signature;
	private LocalDateTime timestamp;
	private String salt;
}
