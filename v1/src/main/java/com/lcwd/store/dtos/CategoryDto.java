package com.lcwd.store.dtos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CategoryDto {

	@Id // important
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private String id;

	@Column(name = "category_name", length = 100, nullable = false)
	private String categoryName;


}
