package com.lcwd.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
