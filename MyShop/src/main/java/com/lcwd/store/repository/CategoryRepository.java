package com.lcwd.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

	List<Category> findByCategoryName(String name);
}
