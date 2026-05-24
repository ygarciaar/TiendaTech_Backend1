package com.example.repository;

import com.example.Pedido;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class PedidoRepository extends GenericRepositoryImpl<Pedido, Long> {
    public PedidoRepository(EntityManager em) {
        super(em, Pedido.class);
    }
}
