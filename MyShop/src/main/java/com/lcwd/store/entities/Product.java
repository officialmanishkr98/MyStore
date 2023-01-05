package com.lcwd.store.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@Entity
public class Product {

	@Id
	private String id;
	
	private int quantity;
	
	private String colour;
	
	private String model;
	
	private int price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
}
