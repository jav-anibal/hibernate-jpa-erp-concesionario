package org.backend.service;

import jakarta.persistence.EntityManager;
import org.backend.model.Coche;
import org.backend.model.Concesionario;
import org.backend.model.Equipamiento;
import org.backend.model.Mecanico;
import org.backend.model.Reparacion;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CocheService {

    public static void insertarCoche(String matricula, String marca,
                                     String modelo, BigDecimal precioBase,
                                     Long idConcesionario) {

        EntityManager conexion = null;
        try {
            // Traemos la conexión -> NOS TRAEMOS LA CONEXIÓN - LA GUARDAMOS EN LA VARIABLE conexion
            conexion = GestorEntityManager.getEntityManager();

            conexion.getTransaction().begin();

            //-> Necesitamos saber si idConcesionario existe
            Concesionario concesionario = conexion.find(Concesionario.class, idConcesionario);
            if (concesionario == null) {
                throw new ApplicationException("No existe concesionario con ID: " + idConcesionario);
            }
            // -> SI existe creamos el objeto completo y asignado

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

    //==========================================================
    // PUNTO 3.1 -> INSTALAR EXTRA
    // ==========================================================

    public static BigDecimal instalarExtra(String matricula, Long idEquipamiento) {

        EntityManager conexion = null;
        try {
            conexion = GestorEntityManager.getEntityManager();
            conexion.getTransaction().begin();

            // 1. Buscar coche por matrícula
            Coche coche = conexion.find(Coche.class, matricula);
            if (coche == null) {
                throw new ApplicationException("No existe coche con matrícula: " + matricula);
            }

            // 2. Buscar equipamiento por ID
            Equipamiento equipamiento = conexion.find(Equipamiento.class, idEquipamiento);
            if (equipamiento == null) {
                throw new ApplicationException("No existe equipamiento con ID: " + idEquipamiento);
            }

            // 3. Añadir equipamiento al coche (relación N:M)
            coche.getEquipamientos().add(equipamiento);

            // 4. Calcular precio total = precioBase + Σ(extras)
            BigDecimal precioTotal = coche.getPrecioBase();
            for (Equipamiento e : coche.getEquipamientos()) {
                precioTotal = precioTotal.add(e.getPrecioUnit());
            }

            conexion.getTransaction().commit();
            System.out.println(" SE HA INSTALADO EL EXTRA CORRECTAMENTE ");

            return precioTotal;

        } catch (ApplicationException e) {
            if (conexion != null && conexion.getTransaction().isActive()) {
                conexion.getTransaction().rollback();
            }
            throw e;
        } catch (Exception e) {
            if (conexion != null && conexion.getTransaction().isActive()) {
                conexion.getTransaction().rollback();
            }
            throw new ApplicationException("ERROR AL INSTALAR EXTRA: " + e.getMessage());
        }

    }

    //==========================================================
    // PUNTO 3.2 -> REGISTRAR REPARACIÓN
    // ==========================================================

    public static void registrarReparacion(String matricula, Long idMecanico,
                                           LocalDate fecha, BigDecimal coste,
                                           String descripcion) {

        EntityManager conexion = null;
        try {
            conexion = GestorEntityManager.getEntityManager();
            conexion.getTransaction().begin();

            // 1. Buscar coche por matrícula
            Coche coche = conexion.find(Coche.class, matricula);
            if (coche == null) {
                throw new ApplicationException("No existe coche con matrícula: " + matricula);
            }

            // 2. Buscar mecánico por ID
            Mecanico mecanico = conexion.find(Mecanico.class, idMecanico);
            if (mecanico == null) {
                throw new ApplicationException("No existe mecánico con ID: " + idMecanico);
            }

            // 3. Crear la reparación
            Reparacion reparacion = new Reparacion(fecha, coste, descripcion, coche, mecanico);

            // 4. Persistir
            conexion.persist(reparacion);

            conexion.getTransaction().commit();
            System.out.println(" SE HA REGISTRADO LA REPARACIÓN CORRECTAMENTE ");

        } catch (ApplicationException e) {
            if (conexion != null && conexion.getTransaction().isActive()) {
                conexion.getTransaction().rollback();
            }
            throw e;
        } catch (Exception e) {
            if (conexion != null && conexion.getTransaction().isActive()) {
                conexion.getTransaction().rollback();
            }
            throw new ApplicationException("ERROR AL REGISTRAR REPARACIÓN: " + e.getMessage());
        }

    }
}
