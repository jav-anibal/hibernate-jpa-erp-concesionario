package org.backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GestorEntityManager {

    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    // 1.1 - Iniciar EntityManager
    public static boolean iniciarConexion() {
        try {
            if (factory == null) {
                factory = Persistence.createEntityManagerFactory("concesionariosPU");
                entityManager = factory.createEntityManager();
                System.out.println("Conexión establecida correctamente");
                return true;
            }
            System.out.println("Ya hay una conexión activa");
            return true;
        } catch (Exception e) {
            System.err.println("Error al establecer conexión: " + e.getMessage());
            return false;
        }
    }

    //=========================================================================================
    // CONEXIÓN -> IMPORTANTE PARA TRABAJAR CON LOS MÉTODOS
    // VERSIÓN MANUAL: Requiere inicialización ELEGIR -> (opción 1.1)
    public static EntityManager getEntityManager() throws ApplicationException {
        if (entityManager == null) {
            throw new ApplicationException("EntityManager no inicializado. Ejecuta primero la opción 1.1");
        }
        return entityManager;
    }
    //=========================================================================================



    // VERSIÓN CON AUTO-INICIALIZACIÓN
    // public static EntityManager getEntityManager() {
    //     if (entityManager == null) {
    //         System.out.println("EntityManager MODO -> Auto-inicializando...");
    //         iniciarConexion();
    //     }
    //     return entityManager;
    // }

    public static void cerrarConexion() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
        System.out.println("Conexión cerrada");
    }
}