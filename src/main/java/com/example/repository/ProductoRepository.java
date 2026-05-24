package com.example.repository;

import com.example.Producto;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class ProductoRepository extends GenericRepositoryImpl<Producto, Long> {
    public ProductoRepository(EntityManager em) {
        super(em, Producto.class);
    }
}
