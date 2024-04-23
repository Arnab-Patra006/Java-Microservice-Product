package com.microservice.productregistry.productservice.ProductCustomExceptions;

public class MyProductByIdNotPresentException extends Exception {
	public MyProductByIdNotPresentException(String errorMessage) {
		super(errorMessage);
	}
}
