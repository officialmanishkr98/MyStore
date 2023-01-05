package com.lcwd.store.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private int cartId;
	
	private Date cartCreatedDate;
	
	
	private List<CartItemDto> items = new ArrayList<>();
	
		
	private UserDto user;
}
