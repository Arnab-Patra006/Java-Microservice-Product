package com.microservice.productregistry.productservice.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.productregistry.productservice.ProductCustomExceptions.MyProductByIdNotPresentException;
import com.microservice.productregistry.productservice.ProductCustomExceptions.MyProductDBEditNotPossibleException;
import com.microservice.productregistry.productservice.dao.ProductDao;
import com.microservice.productregistry.productservice.entity.Product;
import com.microservice.productregistry.productservice.entity.ProductInterface;
import com.microservice.productregistry.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productdao;

	public ResponseEntity<String> addNewProduct(Product product) throws MyProductDBEditNotPossibleException {
		try {
			productdao.save(product);
			return new ResponseEntity<>("new Product added Successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new MyProductDBEditNotPossibleException("Invalid Product Add Request");
		}
	}

	public ResponseEntity<ProductInterface> getProductById(Integer productid) throws MyProductByIdNotPresentException {
		try {
			Product product = productdao.findById(productid).get();
			ProductInterface productInterface = new ProductInterface();
			productInterface.setProductid(productid);
			productInterface.setProductname(product.getTitle());
			productInterface.setProductPrice(product.getPrice());
			productInterface.setCategory(product.getCategory());
			productInterface.setAvailability(product.getAvailability());
			return new ResponseEntity<>(productInterface, HttpStatus.OK);
		} catch (Exception e) {
			throw new MyProductByIdNotPresentException("There is no such product with id " + productid);
		}
	}

	// http://localhost:8090/product/filter?category=jewelery
	public ResponseEntity<List<ProductInterface>> getProductByCategory(String category)
			throws MyProductByIdNotPresentException {
		List<Product> allProducts = new ArrayList<>();
		try {
			allProducts = productdao.findByCategory(category);
		} catch (Exception e) {
			throw new MyProductByIdNotPresentException("There is no such product with Category " + category);
		}
		List<ProductInterface> prodInterfaces = new ArrayList<>();
		for (Product product : allProducts) {
			if (product.getCategory().equals(category) && product.getAvailability() > 0) {
				ProductInterface pI = new ProductInterface();
				pI.setProductid(product.getId());
				pI.setProductname(product.getTitle());
				pI.setProductPrice(product.getPrice());
				pI.setAvailability(product.getAvailability());
				pI.setCategory(product.getCategory());
				prodInterfaces.add(pI);
			}
		}
		return new ResponseEntity<>(prodInterfaces, HttpStatus.OK);
	}

	public ResponseEntity<List<ProductInterface>> getAllProducts() throws MyProductByIdNotPresentException {
		List<Product> allProducts = new ArrayList<>();
		try {
			allProducts = productdao.findAll();
		} catch (Exception e) {
			throw new MyProductByIdNotPresentException("Error in finding All Products");
		}
		List<ProductInterface> prodInterface = new ArrayList<>();
		allProducts = productdao.findAll();
		for (Product product : allProducts) {
			ProductInterface pI = new ProductInterface();
			pI.setProductid(product.getId());
			pI.setProductname(product.getTitle());
			pI.setProductPrice(product.getPrice());
			pI.setAvailability(product.getAvailability());
			pI.setCategory(product.getCategory());
			prodInterface.add(pI);
		}
		return new ResponseEntity<>(prodInterface, HttpStatus.OK);
	}

	public ResponseEntity<Product> updateProduct(Integer productid, Product newProduct)
			throws MyProductByIdNotPresentException {
		Product existingProduct = new Product();
		try {
			existingProduct = productdao.findById(productid).get();
		} catch (Exception e) {
			throw new MyProductByIdNotPresentException("There is no such product with id :" + productid);
		}
		existingProduct.setTitle(newProduct.getTitle());
		existingProduct.setAvailability(newProduct.getAvailability());
		existingProduct.setCategory(newProduct.getCategory());
		existingProduct.setImage(newProduct.getImage());
		existingProduct.setPrice(newProduct.getPrice());
		existingProduct.setRating(newProduct.getRating());
		existingProduct.setDescription(newProduct.getDescription());
		return new ResponseEntity<>(productdao.save(existingProduct), HttpStatus.OK);
	}

	public ResponseEntity<ProductInterface> updateProductByQuantity(Integer productid, Integer quantity)
			throws MyProductDBEditNotPossibleException, MyProductByIdNotPresentException {
		try {
			Product p = new Product();
			try {
				p = productdao.findById(productid).get();
			} catch (Exception e) {
				throw new MyProductByIdNotPresentException(
						"There is no such product with id :" + productid + " to be Updated by Quantity");
			}
			p.setAvailability(quantity);
			productdao.save(p);
			ProductInterface pI = new ProductInterface();
			pI.setAvailability(p.getAvailability());
			pI.setCategory(p.getCategory());
			pI.setProductid(p.getId());
			pI.setProductname(p.getTitle());
			pI.setProductPrice(p.getPrice());
			return new ResponseEntity<>(pI, HttpStatus.OK);
		} catch (Exception e) {
			throw new MyProductDBEditNotPossibleException("Invalid Product Updation Request");
		}
	}

	public ResponseEntity<String> deleteProduct(Integer productid) throws MyProductDBEditNotPossibleException {
		try {
			productdao.deleteById(productid);
			return new ResponseEntity<>("Product deleted from Database Successfully", HttpStatus.OK);
		} catch (Exception e) {
			throw new MyProductDBEditNotPossibleException("Invalid Product Deletion Request");
		}
	}

}
