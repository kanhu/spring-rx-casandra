package com.example.springrx.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.example.springrx.model.Product;
import com.example.springrx.model.ProductIdentity;

public interface ProductRepositry extends  ReactiveCassandraRepository<Product, ProductIdentity>  {

}
