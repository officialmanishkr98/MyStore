package com.lcwd.store.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.store.dtos.ApiResponse;
import com.lcwd.store.dtos.ProductDto;
import com.lcwd.store.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	private Logger logger = LoggerFactory.getLogger(ProductController.class);

	// create
	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
		ProductDto productDto2 = productService.addProduct(productDto);
		return new ResponseEntity<ProductDto>(productDto2, HttpStatus.CREATED);
	}

	// get single.

	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {
		ProductDto product = productService.getProduct(productId);
		return ResponseEntity.ok(product);
	}

	// get all

	@GetMapping
	public ResponseEntity<List<ProductDto>> getProducts() {
		return ResponseEntity.ok(productService.getAll());
	}

	// delete
	@DeleteMapping("/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String productId) {
		productService.deletProduct(productId);
		return ResponseEntity.ok(ApiResponse.builder().message("Product is deleted").success(true).build());
	}

	// update
	@PutMapping("/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable String productId) {
		ProductDto productDto2 = productService.updateProduct(productDto, productId);
		return new ResponseEntity<ProductDto>(productDto2, HttpStatus.OK);
	}

	// search

	// get single.

//	@GetMapping("/search/{keywords}")
//	public ResponseEntity<List<ProductDto>> searchProduct(@PathVariable String keywords) {
//		List<ProductDto> product = productService.searchProduct(keywords);
//		return ResponseEntity.ok(product);
//	}

	// // method for handling exception
	// @ExceptionHandler(ResourceNotFountException.class)
	// public ResponseEntity<ApiResponse>
	// handleRunTimeException(ResourceNotFountException e){
	//
	// logger.info("Runtime Exception Generated : {} ",e.getMessage());
	// return new
	// ResponseEntity<ApiResponse>(ApiResponse.builder().message(e.getMessage()).success(false).build(),
	// HttpStatus.NOT_FOUND);
	// }

}
