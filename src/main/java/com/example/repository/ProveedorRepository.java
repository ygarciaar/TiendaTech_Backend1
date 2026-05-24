package com.example.repository;

import com.example.Proveedor;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class ProveedorRepository extends GenericRepositoryImpl<Proveedor, Long> {
    public ProveedorRepository(EntityManager em) {
        super(em, Proveedor.class);
    }
}
