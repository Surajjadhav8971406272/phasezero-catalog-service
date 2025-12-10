package com.phasezero.catalog_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "partNumber is required")
    @Column(unique = true, nullable = false)
    private String partNumber;

    @NotBlank(message = "partName is required")
    @Column(nullable = false)
    private String partName;

    @NotBlank(message = "category is required")
    @Column(nullable = false)
    private String category;

    @PositiveOrZero(message = "price cannot be negative")
    @Column(nullable = false)
    private double price;

    @PositiveOrZero(message = "stock cannot be negative")
    @Column(nullable = false)
    private int stock;
}