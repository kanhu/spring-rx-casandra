package com.example.springrx.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.springrx.model.Product;
import com.example.springrx.model.ProductIdentity;

@Repository
public interface ProductRepositry extends  ReactiveCassandraRepository<Product, ProductIdentity>  {

}
