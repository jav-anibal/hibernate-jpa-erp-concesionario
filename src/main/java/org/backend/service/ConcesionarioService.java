package org.backend.service;

import jakarta.persistence.EntityManager;
import org.backend.model.Concesionario;

public class ConcesionarioService {

    public static void altaConcesionario(String nombre, String direccion){

        EntityManager conexion = GestorEntityManager.getEntityManager();

        try {

            conexion.getTransaction().begin();

            Concesionario nuevoConcesionario = new Concesionario(nombre, direccion);
            conexion.persist(nuevoConcesionario);

            conexion.getTransaction().commit();
            System.out.println("Se ha dado de alta un nuevo concesionario");
            System.out.println("Concesionario: ID: " + nuevoConcesionario.getId());

        } catch (Exception e) {

            if (conexion.getTransaction().isActive()) {
                conexion.getTransaction().rollback();
            }
            throw new ApplicationException("Error al insertar concesionario: " + e.getMessage(), e);
        }


    }
}
