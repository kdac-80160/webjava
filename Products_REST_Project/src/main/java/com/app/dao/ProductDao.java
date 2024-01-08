package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Category;
import com.app.entities.Products;

public interface ProductDao extends JpaRepository<Products, Integer> {
	List<Products> findAllByCategory(Category category);
}
