package com.lcwd.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

}
