package com.product.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.entity.Product;
import com.product.exception.ProductServiceException;
import com.product.model.OrderItem;
import com.product.model.ProductRequest;
import com.product.model.ProductResponse;
import com.product.service.ProductService;
import com.product.service.repo.ProductRepo;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	
    @Override
    public long addProduct(ProductRequest productRequest) {
       log.info("Adding Product..");

        Product product
                = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepo.save(product);

        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for productId: {}", productId);

        Product product
                = productRepo.findById(productId)
                .orElseThrow(
                        () -> new ProductServiceException("Product with given id not found","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse
                = new ProductResponse();

        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity,productId);

        Product product
                = productRepo.findById(productId)
                .orElseThrow(() -> new ProductServiceException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepo.save(product);
        log.info("Product Quantity updated Successfully");
    }
	



}





