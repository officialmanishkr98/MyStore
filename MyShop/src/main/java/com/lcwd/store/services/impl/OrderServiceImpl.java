package com.lcwd.store.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.store.dtos.CreateOrderRequest;
import com.lcwd.store.dtos.OrderDto;
import com.lcwd.store.entities.Cart;
import com.lcwd.store.entities.CartItem;
import com.lcwd.store.entities.Order;
import com.lcwd.store.entities.OrderItem;
import com.lcwd.store.entities.User;
import com.lcwd.store.exceptions.BadRequestException;
import com.lcwd.store.exceptions.ResourceNotFoundException;
import com.lcwd.store.repository.CartRepository;
import com.lcwd.store.repository.OrderRepository;
import com.lcwd.store.repository.UserRepository;
import com.lcwd.store.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public OrderDto createOrder(CreateOrderRequest orderRequest, String userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		int cartId = orderRequest.getCartId();

		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cart not found"));

		List<CartItem> cartItems = cart.getItems();

		if (cartItems.size() <= 0) {
			throw new BadRequestException("Invalid nuber of items in cart!!");
		}

		Order order = Order.builder().billingName(orderRequest.getBillingName())
				.billingPhone(orderRequest.getBillingPhone()).bllingAddress(orderRequest.getBillingAddress())
				.deliveryDate(orderRequest.getDeliveredDate()).orderDate(orderRequest.getOrderDate())
				.orderStatus(orderRequest.getOrderStatus()).paymentStatus(orderRequest.getPaymentStatus())
				.orderId(UUID.randomUUID().toString()).user(user).build();

		AtomicReference<Integer> orderTotalAmount = new AtomicReference<>(0);
		List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
			OrderItem orderItem = OrderItem.builder().quantity(cartItem.getQuantity()).product(cartItem.getProduct())
					.order(order).totalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice()).build();
			orderTotalAmount.set(orderTotalAmount.get() + orderItem.getTotalPrice());

			return orderItem;

		}).collect(Collectors.toList());

		order.setOrderItems(orderItems);
		order.setTotalAmount(orderTotalAmount.get());

		Order savedOrder = orderRepository.save(order);

		// card items set to blank once order is placed
		cart.getItems().clear();
		cartRepository.save(cart);

		return mapper.map(savedOrder, OrderDto.class);
	}
	
	// recheck these below methods

	@Override
	public void deleteOrder(String orderId) {
		orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found!")).setOrderStatus("Order cancelled!!");

	}

	@Override
	public List<OrderDto> getOrdersOfUsers(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		List<Order> list = orderRepository.findAll();
		  List<Order> orders = list.stream().filter(order -> order.getUser()==user).collect(Collectors.toList());
		  List<OrderDto> list2 = orders.stream().map(order -> mapper.map(orders, OrderDto.class)).collect(Collectors.toList());
		  return list2;
		 
	}

	@Override
	public List<OrderDto> getOrders() {
		List<Order> list = orderRepository.findAll();
		List<OrderDto> list2 = list.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
		return list2;
	}

}
