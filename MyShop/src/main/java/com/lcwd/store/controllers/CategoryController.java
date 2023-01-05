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
import com.lcwd.store.dtos.CategoryDto;
import com.lcwd.store.dtos.ProductDto;
import com.lcwd.store.services.CategoryServices;
import com.lcwd.store.services.ProductServices;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryServices categoryServices;

	@Autowired
	private ProductServices productServices;

	// create
	@PostMapping
	public ResponseEntity<CategoryDto> addEntity(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto addCategoryDto = categoryServices.addCategoryDto(categoryDto);
		return new ResponseEntity<CategoryDto>(addCategoryDto, HttpStatus.CREATED);
	}

	// create product
	@PostMapping("/{categoryId}/products")
	public ResponseEntity<ProductDto> createProuct(@Valid @RequestBody ProductDto productDto,
			@PathVariable String categoryId) {

		ProductDto productDto2 = productServices.addProduct(productDto, categoryId);
		return new ResponseEntity<ProductDto>(productDto2, HttpStatus.CREATED);
	}

	// get all
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAll() {
		List<CategoryDto> allcaCategoryDtos = categoryServices.getAll();
		return ResponseEntity.ok(allcaCategoryDtos);
	}

	// get one
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable String categoryId) {
		CategoryDto categoryDto = categoryServices.getOne(categoryId);
		return ResponseEntity.ok(categoryDto);
	}

	// update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updatEntity(@PathVariable String categoryId,
			@RequestBody CategoryDto categoryDto) {
		CategoryDto categoryDto2 = categoryServices.updateCategoryDto(categoryDto, categoryId);

		return ResponseEntity.ok(categoryDto2);
	}

	// update category
	@PostMapping("/{categoryId}/products/{productId}")
	public ResponseEntity<ProductDto> updateCategory(@PathVariable String categoryId, @PathVariable String productId) {
		ProductDto updateProductCategory = productServices.updateProductCategory(categoryId, productId);
		return ResponseEntity.ok(updateProductCategory);
	}

	// delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deletEntity(@PathVariable String categoryId) {
		categoryServices.delete(categoryId);
		return new ResponseEntity<ApiResponse>(
				ApiResponse.builder().message("category with id deleted").success(false).build(), HttpStatus.ACCEPTED);

	}

	// search
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<CategoryDto>> searchEntity(@PathVariable String keywords) {

		return ResponseEntity.ok(categoryServices.searchCategoryDto(keywords));
	}
}
