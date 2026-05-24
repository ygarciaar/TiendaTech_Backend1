package com.example.repository;

import com.example.Usuario;
import com.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class UsuarioRepository extends GenericRepositoryImpl<Usuario, Long> {
    public UsuarioRepository(EntityManager em) {
        super(em, Usuario.class);
    }
}
