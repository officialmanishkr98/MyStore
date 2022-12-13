package com.lcwd.store.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lcwd.store.dtos.CategoryDto;
import com.lcwd.store.entities.Category;
import com.lcwd.store.excetions.ResourceNotFountException;
import com.lcwd.store.respository.CategoryRepository;
import com.lcwd.store.services.CategoryService;

@Service
@Primary
public class CategoryServiceJpaImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
    	
    	Category category = mapper.map(categoryDto, Category.class);
    	
    	category.setId(UUID.randomUUID().toString());
    	
    	Category savedCategory = categoryRepository.save(category);
    	
    	CategoryDto savedCategoryDto = mapper.map(savedCategory, CategoryDto.class);
    	
        return savedCategoryDto;
        
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {

        // get the old user:
    	Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFountException("Category with given id not found on server !!"));
    	category.setCategoryName(   categoryDto.getCategoryName()   );

  
        // update
    	Category updatedCategory = categoryRepository.save(category);
        return mapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(String categoryId) {
    	Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFountException("Category with given id not found on server !!"));
        
        return mapper.map( category , CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll(){
    	
        List<Category> categorys = categoryRepository.findAll();
        return categorys.stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deletCategory(String categoryId) {
    	Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFountException("Category with given id not found on server !!"));
    	categoryRepository.delete(category);
    }

}
