package com.microservice.productregistry.productservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.productregistry.productservice.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM PRODUCT P WHERE P.PRODUCT_CATEGORY=:category", nativeQuery = true)
	List<Product> findByCategory(@Param("category") String category);
}
