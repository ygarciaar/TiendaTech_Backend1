package com.example.repository;

import com.example.Client;
import com.example.repository.impl.GenericRepositoryImpl;
import jakarta.persistence.EntityManager;

public class ClientRepository extends GenericRepositoryImpl<Client, Long> {
    public ClientRepository(EntityManager em) {
        super(em, Client.class);
    }
}
