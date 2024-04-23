package com.microservice.productregistry.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private Integer id;

	@Column(name = "productName")
	private String title;
	@Column(name = "productPrice")
	private Double price;
	@Column(name = "productDescription", length = 1500)
	private String description;
	@Column(name = "productCategory")
	private String category;
	@Column(name = "productImageUrl")
	private String image;
	@Column(name = "productAvailability")
	private Integer availability;
	@Embedded
	private Rating rating;
}
