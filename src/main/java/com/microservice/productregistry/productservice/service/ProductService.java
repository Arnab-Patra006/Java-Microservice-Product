package com.microservice.productregistry.productservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.microservice.productregistry.productservice.ProductCustomExceptions.MyProductByIdNotPresentException;
import com.microservice.productregistry.productservice.ProductCustomExceptions.MyProductDBEditNotPossibleException;
import com.microservice.productregistry.productservice.entity.Product;
import com.microservice.productregistry.productservice.entity.ProductInterface;

public interface ProductService {
	public ResponseEntity<String> addNewProduct(Product product) throws MyProductDBEditNotPossibleException;

	public ResponseEntity<ProductInterface> getProductById(Integer productid) throws MyProductByIdNotPresentException;

	public ResponseEntity<List<ProductInterface>> getAllProducts() throws MyProductByIdNotPresentException;

	public ResponseEntity<String> deleteProduct(Integer productid) throws MyProductDBEditNotPossibleException;

	public ResponseEntity<Product> updateProduct(Integer productid, Product newProduct)
			throws MyProductByIdNotPresentException;

	public ResponseEntity<List<ProductInterface>> getProductByCategory(String category)
			throws MyProductByIdNotPresentException;

	public ResponseEntity<ProductInterface> updateProductByQuantity(Integer productid, Integer quantity)
			throws MyProductDBEditNotPossibleException, MyProductByIdNotPresentException;
}
