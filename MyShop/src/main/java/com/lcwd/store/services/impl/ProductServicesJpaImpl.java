package com.lcwd.store.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.store.dtos.ProductDto;
import com.lcwd.store.entities.Category;
import com.lcwd.store.entities.Product;
import com.lcwd.store.exceptions.ResourceNotFoundException;
import com.lcwd.store.repository.CategoryRepository;
import com.lcwd.store.repository.ProductRepository;
import com.lcwd.store.services.ProductServices;

@Service
public class ProductServicesJpaImpl implements ProductServices {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public ProductDto addProduct(ProductDto productDto) {

		Product product = mapper.map(productDto, Product.class);
		product.setId(UUID.randomUUID().toString());
		Product product2 = productRepository.save(product);
		return mapper.map(product2, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, String productDtoId) {

		Product product = productRepository.findById(productDtoId)
				.orElseThrow(() -> new ResourceNotFoundException("product with the given id not found"));
		product.setColour(productDto.getColour());
		product.setModel(productDto.getModel());
		product.setPrice(productDto.getPrice());
		product.setQuantity(productDto.getQuantity());

		Product savedProduct = productRepository.save(product);
		return mapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(String productDtoId) {
		productRepository.deleteById(productDtoId);

	}

	@Override
	public ProductDto getProduct(String productDtoId) {
		Product product = productRepository.findById(productDtoId)
				.orElseThrow(() -> new ResourceNotFoundException("product with the given id not found"));

		return mapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProduct() {
		List<Product> findAllproducts = productRepository.findAll();
		List<ProductDto> productDtosList = findAllproducts.stream()
				.map(product -> mapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtosList;
	}

	@Override
	public List<ProductDto> searchProduct(int keyword) {
		List<Product> productInRange = productRepository.findProductWithPriceInRange(keyword);
		List<ProductDto> list = productInRange.stream().map(product -> mapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public ProductDto addProduct(ProductDto productDto, String categoryId) {
		
		// get the category of given id
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category with the given id not found"));
		Product product = mapper.map(productDto, Product.class);
		product.setCategory(category);
		product.setId(UUID.randomUUID().toString());
		Product savedProduct = productRepository.save(product);
		return mapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProductCategory(String categoryId, String productDtoId) {
		Product product = productRepository.findById(productDtoId).orElseThrow(() -> new ResourceNotFoundException("product with given id not found"));
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category with given id not found"));
		product.setCategory(category);
		Product product2 = productRepository.save(product);
		return mapper.map(product2, ProductDto.class);
	}

}
