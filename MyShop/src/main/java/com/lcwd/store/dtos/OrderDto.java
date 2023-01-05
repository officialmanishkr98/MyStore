package com.lcwd.store.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



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
public class OrderDto {

	private String orderId;
	
	private String billingPhone;

	private String billingName;
	
	private String orderStatus;
	
	private String paymentStatus;
	
	private int totalAmount;
	
	private String bllingAddress;
	
	private Date orderDate;
	
	private Date deliveryDate;
	
	
	//private UserDto user;
	
	
	private List<OrderItemDto> orderItems = new ArrayList<>();
	

}
