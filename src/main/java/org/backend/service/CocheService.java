package org.backend.service;

import jakarta.persistence.EntityManager;
import org.backend.model.Coche;
import org.backend.model.Concesionario;

import java.math.BigDecimal;

public class CocheService {

    public static void insertarCoche(String matricula, String marca,
                                     String modelo, BigDecimal precioBase,
                                     Long idConcesionario) {

        EntityManager conexion = null;
        try {
            // Traemos la conexión -> //por completa esta información xq sigo estructurando
            conexion = GestorEntityManager.getEntityManager();

            conexion.getTransaction().begin();

            //-> Necesitamos saber si idConcesionario existe
            Concesionario concesionario = conexion.find(Concesionario.class, idConcesionario);
            if (concesionario == null) {
                throw new ApplicationException("No existe concesionario con ID: " + idConcesionario);
            }
            // -> SI existe creamos el objeto comleto y asignado

            Coche nuevoCoche = new Coche(matricula, marca, modelo, precioBase, concesionario);

            conexion.persist(nuevoCoche);// se guarda en nuestra base de datos |PERSISTENCIA|

            conexion.getTransaction().commit(); // confirmamos cambios
            System.out.println(" HA -INSERTADO UN COCHE NUEVO ");

        } catch (Exception e) {
            if (conexion.getTransaction().isActive()) {
                conexion.getTransaction().rollback();
            }
            System.err.println("El matricula ya se encuentra en el sistema");
            throw new ApplicationException("ERROR AL INSERTAR COCHE");
        }


    }
}
