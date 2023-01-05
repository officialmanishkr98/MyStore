package com.lcwd.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

private int orderItemId;
	
	private int quantity;
	
	
    private ProductDto product;
	
	private int totalPrice;
}
