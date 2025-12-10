package com.phasezero.catalog_service.model;

import java.time.Instant;

import lombok.Data;
@Data
public class Product {
	
	private String id;
	private String partNumber;
	private String partName;
	private String catagory;
	private double prize;
	private int stock;
	private Instant createdAt;
	
	
	

}
