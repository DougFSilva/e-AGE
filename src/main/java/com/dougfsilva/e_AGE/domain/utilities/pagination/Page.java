package com.dougfsilva.e_AGE.domain.utilities.pagination;

import java.util.List;

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
public class Page<T> {

	private List<T> content;
	private Integer number;
	private Integer size;
	private Long totalElements;
	private Long totalPages;
	private Boolean hasContent;
	private Boolean isFirst;
	private Boolean isLast;

}
