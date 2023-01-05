package com.lcwd.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
 
}
