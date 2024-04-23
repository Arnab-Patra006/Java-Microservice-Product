package com.microservice.productregistry.productservice.ProductCustomExceptions;

public class MyProductDBEditNotPossibleException extends Exception {
	public MyProductDBEditNotPossibleException(String errorMessage) {
		super(errorMessage);
	}
}
