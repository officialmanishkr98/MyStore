package com.lcwd.store.services;

import java.util.List;

import com.lcwd.store.dtos.CategoryDto;

public interface CategoryService {

	// add category
	CategoryDto addCategory(CategoryDto categoryDto);

	// update
	CategoryDto updateCategory(CategoryDto category, String categoryId);

	// get single
	CategoryDto getCategory(String categoryId);

	// get All
	List<CategoryDto> getAll();

	// delete category
	void deletCategory(String categoryId);

}
