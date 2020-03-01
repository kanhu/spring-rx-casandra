package com.example.springrx.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("product")
public class Product {
	@PrimaryKey
	ProductIdentity productIdentity;
	String colorCode;
	String name;
	String description;
	double price;
}
