package com.product.exception;

import lombok.Data;

@Data
public class ProductServiceException extends RuntimeException{
	
	private String errorCode;
	private String errorMsg;
	 public ProductServiceException(String msg , String errorcode)
	{
		  
		super();
		this.errorCode = errorcode;
		this.errorMsg = msg;
		
	}

}
