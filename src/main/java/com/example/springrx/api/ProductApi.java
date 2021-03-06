package com.example.springrx.api;

import java.util.List;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrx.model.Product;
import com.example.springrx.model.ProductIdentity;
import com.example.springrx.service.ProductService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1")
public class ProductApi {

	@Autowired
	ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(Product product) {
		productService.save(product);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/products")
	public Publisher<Product> getProducts(List<ProductIdentity> identities) {
		return productService.getProducts(identities);
	}
	
	

}
