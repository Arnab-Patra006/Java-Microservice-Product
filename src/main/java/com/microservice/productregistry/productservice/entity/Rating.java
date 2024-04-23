package com.microservice.productregistry.productservice.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Rating {
	private Double rate;
	private Double count;
}
