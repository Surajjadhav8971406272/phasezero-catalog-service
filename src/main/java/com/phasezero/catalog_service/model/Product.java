package com.phasezero.catalog_service.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Product {
	
	@Id
	private String partNumber;
	private String partName;
	private String catagory;
	private double prize;
	private int stock;
	private Instant createdAt;
	
	
	

}
