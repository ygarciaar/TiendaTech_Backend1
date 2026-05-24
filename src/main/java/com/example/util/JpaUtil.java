package com.example.util;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            Map<String, String> properties = new HashMap<>();
            properties.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("jakarta.persistence.jdbc.url", "jdbc:postgresql://pooled.db.prisma.io:5432/postgres?sslmode=require");
            properties.put("jakarta.persistence.jdbc.user", "65f4ab1ae8604fb348c505efcba9625b6ad3e8e92b5f9701056115c77ffc7f22");
            properties.put("jakarta.persistence.jdbc.password", "sk_4RemiUQmMePowhwnqua_A");
            
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");
            properties.put("hibernate.hbm2ddl.auto", "update");

            return Persistence.createEntityManagerFactory("miUnidadPersistencia", properties);
        } catch (Throwable ex) {
            System.err.println("Error crítico al iniciar Hibernate en JpaUtil: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // El método que tu Main.java necesita para obtener la conexión
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    // El método que tu Main.java necesita para cerrar la conexión
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}