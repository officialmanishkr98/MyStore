package com.lcwd.store.services;

import java.util.List;

import com.lcwd.store.dtos.CreateOrderRequest;
import com.lcwd.store.dtos.OrderDto;

public interface OrderService {

	//create order
	OrderDto createOrder(CreateOrderRequest orderRequest, String userId);
	
	//delete order
	void deleteOrder(String orderId);
	
	//get orders of user
	List<OrderDto> getOrdersOfUsers(String userId);
	
	//get all orders
	List<OrderDto> getOrders();
}
