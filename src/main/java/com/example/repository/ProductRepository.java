package com.example.repository;

import com.example.Product;
import com.example.repository.impl.GenericRepositoryImpl;
import jakarta.persistence.EntityManager;

public class ProductRepository extends GenericRepositoryImpl<Product, Long> {
    public ProductRepository(EntityManager em) {
        super(em, Product.class);
    }
}
