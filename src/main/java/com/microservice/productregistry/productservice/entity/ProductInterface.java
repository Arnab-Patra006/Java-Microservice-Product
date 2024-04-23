package com.microservice.productregistry.productservice.entity;

import lombok.Data;

@Data
public class ProductInterface {
	private Integer productid;
	private String productname;
	private Integer availability;
	private Double productPrice;
	private String category;
}
