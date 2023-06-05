package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponceEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ProductServiceException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorMessage>	handleProductIdException(ProductServiceException ex)
	{
		return new ResponseEntity<>(new ErrorMessage(ex.getErrorCode(),ex.getErrorMsg()),HttpStatus.BAD_REQUEST);
	}
}
