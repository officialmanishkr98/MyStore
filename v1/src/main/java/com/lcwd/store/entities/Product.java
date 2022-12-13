package com.lcwd.store.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcwd.store.entities.User.UserBuilder;

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
// jpa
@Entity // important
@Table(name = "jpa_product") // optional
public class Product{

	@Id // important
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private String id;

	@Column(name = "user_name", length = 100, nullable = false)
	private String productName;

	private Integer productPrice;

	private Integer productQty;

}
