package com.lcwd.store.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lcwd.store.dtos.ProductDto;
import com.lcwd.store.entities.Product;
import com.lcwd.store.excetions.ResourceNotFountException;
import com.lcwd.store.respository.ProductRepository;
import com.lcwd.store.services.ProductService;

@Service
@Primary
public class ProductServiceJpaImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
    	
    	Product product = mapper.map(productDto, Product.class);
    	
    	product.setId(UUID.randomUUID().toString());
    	
    	Product savedProduct = productRepository.save(product);
    	
    	ProductDto savedProductDto = mapper.map(savedProduct, ProductDto.class);
    	
        return savedProductDto;
        
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String productId) {

        // get the old user:
    	Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFountException("Product with given id not found on server !!"));
    	product.setProductName(   productDto.getProductName()   );
    	product.setProductPrice(  productDto.getProductPrice()  );
    	product.setProductQty(    productDto.getProductQty()    );
  
        // update
    	Product updatedProduct = productRepository.save(product);
        return mapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public ProductDto getProduct(String productId) {
    	Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFountException("Product with given id not found on server !!"));
        
        return mapper.map( product , ProductDto.class);
    }

    @Override
    public List<ProductDto> getAll(){
    	
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deletProduct(String productId) {
    	Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFountException("Product with given id not found on server !!"));
    	productRepository.delete(product);
    }

}
