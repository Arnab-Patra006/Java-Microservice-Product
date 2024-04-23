package com.microservice.productregistry.productservice.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.microservice.productregistry.productservice.ProductCustomExceptions.MyProductByIdNotPresentException;
import com.microservice.productregistry.productservice.ProductCustomExceptions.MyProductDBEditNotPossibleException;

@ControllerAdvice
public class GlobalProductExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = MyProductByIdNotPresentException.class)
	public ResponseEntity<String> MyProductByIdNotPresentExceptionF(
			com.microservice.productregistry.productservice.ProductCustomExceptions.MyProductByIdNotPresentException message) {
		return new ResponseEntity<String>(message.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = MyProductDBEditNotPossibleException.class)
	public ResponseEntity<String> MyProductDBEditNotPossibleExceptionF(MyProductDBEditNotPossibleException message) {
		return new ResponseEntity<String>(message.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
