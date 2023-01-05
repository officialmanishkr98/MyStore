package com.lcwd.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.store.dtos.ApiResponse;
import com.lcwd.store.dtos.CartDto;
import com.lcwd.store.dtos.CartItemRequest;
import com.lcwd.store.services.CartService;

@RestController
@RequestMapping("/carts")
//@Controller
//@ResponseBody
public class CartController {
	
	 String userId="79c3d141-0171-47b9-a40b-2cd833ce8090";
	@Autowired
	private CartService cartService;

	//add item to cart
	
	@PostMapping("/add-item")
	public ResponseEntity<CartDto> addItemToCart(@RequestBody CartItemRequest cartItemRequest){
		CartDto cartDto = cartService.addItemToCart(cartItemRequest, userId);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
	}
	
	@DeleteMapping("/remove-item/{itemId}")
	public ResponseEntity<ApiResponse> removeItem(@PathVariable int itemId){
		cartService.removeItemFromCart(itemId, userId);
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().message("item deleted").success(true).build(),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<CartDto> getCart(){
		CartDto cart = cartService.getCart(userId);
		return ResponseEntity.ok(cart);
	}
	
}
