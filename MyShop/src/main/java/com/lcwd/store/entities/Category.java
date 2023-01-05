package com.lcwd.store.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Entity
public class Category {

	@Id
	private String id;
	
	private String categoryName;
	
	@OneToMany(
			   mappedBy = "category",
			   cascade = CascadeType.REMOVE
			   )
	private List<Product> products = new ArrayList<>();
	
}
