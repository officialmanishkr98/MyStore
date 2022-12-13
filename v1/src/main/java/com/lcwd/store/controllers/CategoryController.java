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
import com.lcwd.store.dtos.CategoryDto;
import com.lcwd.store.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	private Logger logger = LoggerFactory.getLogger(CategoryController.class);

	// create
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto categoryDto2 = categoryService.addCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.CREATED);
	}

	// get single.

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable String categoryId) {
		CategoryDto category = categoryService.getCategory(categoryId);
		return ResponseEntity.ok(category);
	}

	// get all

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCategorys() {
		return ResponseEntity.ok(categoryService.getAll());
	}

	// delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId) {
		categoryService.deletCategory(categoryId);
		return ResponseEntity.ok(ApiResponse.builder().message("Category is deleted").success(true).build());
	}

	// update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String categoryId) {
		CategoryDto categoryDto2 = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.OK);
	}

	// search

	// get single.

//	@GetMapping("/search/{keywords}")
//	public ResponseEntity<List<CategoryDto>> searchCategory(@PathVariable String keywords) {
//		List<CategoryDto> category = categoryService.searchCategory(keywords);
//		return ResponseEntity.ok(category);
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
