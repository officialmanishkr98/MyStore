package com.lcwd.store.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcwd.store.entities.Product.ProductBuilder;

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
@Entity // important
@Table(name = "jpa_category") // optional
public class Category {

	@Id // important
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private String id;

	@Column(name = "category_name", length = 100, nullable = false)
	private String CategoryName;
	
}
