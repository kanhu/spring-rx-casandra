package com.example.springrx.service;

import java.util.List;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springrx.model.Product;
import com.example.springrx.model.ProductIdentity;
import com.example.springrx.repository.ProductRepositry;


public class ProductService {
	@Autowired
	ProductRepositry productRepositry;
	
	public Publisher<Product> getProducts(List<ProductIdentity> ids){
		return productRepositry.findAllById(ids);
	}
	
	public void save(Product product) {
		productRepositry.save(product);
	}
}
