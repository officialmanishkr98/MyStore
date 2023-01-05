package com.lcwd.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.store.dtos.ApiResponse;
import com.lcwd.store.dtos.CreateOrderRequest;
import com.lcwd.store.dtos.OrderDto;
import com.lcwd.store.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	private String userId = "79c3d141-0171-47b9-a40b-2cd833ce8090";
	
	//create
	@PostMapping
	public ResponseEntity<OrderDto> createOrder(
			@RequestBody CreateOrderRequest createOrderRequest){
		
		OrderDto createOrder = orderService.createOrder(createOrderRequest, userId); 
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createOrder);
	}
	
	// delete 
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<ApiResponse> deleteOrder(@PathVariable String orderId){
		orderService.deleteOrder(orderId);
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().message("order cancelled").success(true).build(),HttpStatus.ACCEPTED);
	}
	
	//get by userId
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<OrderDto>> getAll(@PathVariable String userId){
		List<OrderDto> list = orderService.getOrdersOfUsers(userId);
		return ResponseEntity.ok(list);
	}
	
	//find all orders
	@GetMapping
	public ResponseEntity<List<OrderDto>> getOrder(){
		List<OrderDto> list = orderService.getOrders();
		return ResponseEntity.ok(list);
	}
	
}
