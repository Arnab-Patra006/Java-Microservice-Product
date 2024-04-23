package com.microservice.productregistry.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.productregistry.productservice.entity.Product;
import com.microservice.productregistry.productservice.entity.ProductInterface;
import com.microservice.productregistry.productservice.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("product")
public class ProductController {
	@Autowired
	ProductServiceImpl productservice;

	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@RequestBody Product product) throws Exception {
		return productservice.addNewProduct(product);
	}

	@GetMapping("/{productid}")
	public ResponseEntity<ProductInterface> getProductById(@PathVariable Integer productid) throws Exception {
		return productservice.getProductById(productid);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<ProductInterface>> getProductsByCategory(@RequestParam String category)
			throws Exception {
		return productservice.getProductByCategory(category);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ProductInterface>> getAllProducts() throws Exception {
		return productservice.getAllProducts();
	}

	@GetMapping("update/{productid}/{quantity}")
	public ResponseEntity<ProductInterface> updateProductQuantity(@PathVariable Integer productid,
			@PathVariable Integer quantity) throws Exception {
		return productservice.updateProductByQuantity(productid, quantity);
	}

	@PutMapping("/update/{productid}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer productid, @RequestBody Product product)
			throws Exception {
		return productservice.updateProduct(productid, product);
	}

	@DeleteMapping("/delete/{productid}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productid) throws Exception {
		return productservice.deleteProduct(productid);
	}
}
