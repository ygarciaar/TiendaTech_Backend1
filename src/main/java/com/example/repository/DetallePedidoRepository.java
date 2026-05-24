package com.example.repository;

import com.example.DetallePedido;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class DetallePedidoRepository extends GenericRepositoryImpl<DetallePedido, Long> {
    public DetallePedidoRepository(EntityManager em) {
        super(em, DetallePedido.class);
    }
}
