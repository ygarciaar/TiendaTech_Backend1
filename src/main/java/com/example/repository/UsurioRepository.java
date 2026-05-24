package com.example.repository;

import com.example.Usuario;
import com.example.repository.impl.GenericRepositoryImpl;
import jakarta.persistence.EntityManager;

public class UsurioRepository extends GenericRepositoryImpl<Usuario, Long> {
    public UsurioRepository(EntityManager em) {
        super(em, Usuario.class);
    }
}
