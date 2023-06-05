package com.product.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.product.entity.Product;
import com.product.model.OrderItem;
import com.product.model.ProductRequest;
import com.product.model.ProductResponse;

public interface ProductService  {

	 long addProduct(ProductRequest productRequest);

	    ProductResponse getProductById(long productId);

	    void reduceQuantity(long productId, long quantity);
	
	
	
}
