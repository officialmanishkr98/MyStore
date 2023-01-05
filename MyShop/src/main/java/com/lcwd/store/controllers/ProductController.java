package com.lcwd.store.controllers;

import java.util.List;

import javax.validation.Valid;

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
import com.lcwd.store.services.ProductServices;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductServices productServices;
	
	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
		productServices.addProduct(productDto);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/{productDtoId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable String productDtoId){
		ProductDto productDto = productServices.getProduct(productDtoId);
		return ResponseEntity.ok(productDto);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> productsDtos = productServices.getAllProduct();
		return ResponseEntity.ok(productsDtos);
	}
	
	@DeleteMapping("/{productDtoId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String productDtoId){
		productServices.deleteProduct(productDtoId);
		return ResponseEntity.ok(ApiResponse.builder().message("product deleted").success(true).build());
	}
	
	@PutMapping("/{productDtoId}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable String productDtoId){
		productServices.updateProduct(productDto, productDtoId);
		return ResponseEntity.ok(productDto);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<ProductDto>> searchProduct(@PathVariable int keyword){
		List<ProductDto> productDtos =  productServices.searchProduct(keyword);
		return ResponseEntity.ok(productDtos);
	}
	
	
}
