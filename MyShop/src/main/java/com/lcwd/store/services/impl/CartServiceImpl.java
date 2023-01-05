package com.lcwd.store.services.impl;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.lcwd.store.dtos.CartDto;
import com.lcwd.store.dtos.CartItemRequest;
import com.lcwd.store.entities.Cart;
import com.lcwd.store.entities.CartItem;
import com.lcwd.store.entities.Product;
import com.lcwd.store.entities.User;
import com.lcwd.store.exceptions.BadRequestException;
import com.lcwd.store.exceptions.ResourceNotFoundException;
import com.lcwd.store.repository.CartItemRepository;
import com.lcwd.store.repository.CartRepository;
import com.lcwd.store.repository.ProductRepository;
import com.lcwd.store.repository.UserRepository;
import com.lcwd.store.services.CartService; 
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CartDto addItemToCart(CartItemRequest cartItemRequest, String userId) {

		int quantity = cartItemRequest.getQuantity();
		if(quantity <= 0) {
			throw new BadRequestException("quantity is invalid!!");
		}
		String productId = cartItemRequest.getProductId();
		// get the user
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user with the given id not found"));

		// get the product to be added in the cart
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product with the given id not found"));

		Cart cart = null;
		// get cart of user
		try {
			// if cart is present in database
			cart = cartRepository.findByUser(user).get();
		} catch (NoSuchElementException e) {
			// no cart of this user is present in database
			cart = new Cart();
			cart.setCartCreatedDate(new Date());
		}

		AtomicReference<Boolean> updated = new AtomicReference(false);
		List<CartItem> items = cart.getItems();
		
		List<CartItem> updatdItems = items.stream().map(item -> {
			if (item.getProduct().getId().equals(productId)) {
				item.setQuantity(quantity);
				item.setTotalPrice(quantity*product.getPrice());
				updated.set(true);
	 		}
			return item;
	}).collect(Collectors.toList());

		// cart -> cartItem
		if(!updated.get()) {
		CartItem cartItem = CartItem.builder().quantity(quantity).totalPrice(quantity * product.getPrice()).cart(cart)
				.product(product).build();
		cart.getItems().add(cartItem);
		}
		
		cart.setUser(user);
		Cart updatedCart = cartRepository.save(cart);
		return mapper.map(updatedCart, CartDto.class);
	}

	@Override
	public void removeItemFromCart(int itemId, String userId) {

		CartItem item = cartItemRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("item with given id not found"));
		cartItemRepository.delete(item);
	}

	@Override
	public CartDto getCart(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user with given id not found"));

		Cart cart = cartRepository.findByUser(user).get();

		return mapper.map(cart, CartDto.class);
	}

	@Override
	public CartDto getCartByUserEmail(String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("user with given email not found"));

		Cart cart = cartRepository.findByUser(user).get();

		return mapper.map(cart, CartDto.class);

	}

}
