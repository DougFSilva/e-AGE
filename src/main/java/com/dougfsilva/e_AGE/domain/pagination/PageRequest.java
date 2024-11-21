package com.dougfsilva.e_AGE.domain.pagination;

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
public class PageRequest {

	private Integer page;

	private Integer size;

	private Direction direction;

}
