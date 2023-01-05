package com.lcwd.store.dtos;

import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table
public class CategoryDto {

	private String id;

	@Size(min=5,max=15, message = "Username must be between 5 to 15 chars")
	private String categoryName;
}
