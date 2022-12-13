package com.lcwd.store.services;

import java.util.List;

import com.lcwd.store.dtos.ProductDto;

public interface ProductService {

	// add product
	ProductDto addProduct(ProductDto productDto);

	// update
	ProductDto updateProduct(ProductDto product, String productId);

	// get single
	ProductDto getProduct(String productId);

	// get All
	List<ProductDto> getAll();

	// delete product
	void deletProduct(String productId);

}
