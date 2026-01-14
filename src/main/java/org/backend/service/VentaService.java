package org.backend.service;

import jakarta.persistence.EntityManager;
import org.backend.model.Coche;
import org.backend.model.Concesionario;
import org.backend.model.Propietario;
import org.backend.model.Venta;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VentaService {

    //==========================================================
    // PUNTO 4.1 -> VENDER COCHE
    // ==========================================================

    public static void venderCoche(String dni, String nombrePropietario,
                                   String matricula, Long idConcesionario,
                                   BigDecimal precioFinal) {

        EntityManager conexion = null;
        try {
            conexion = GestorEntityManager.getEntityManager();
            conexion.getTransaction().begin();

            // 1. Buscar coche por matrícula
            Coche coche = conexion.find(Coche.class, matricula);
            if (coche == null) {
                throw new ApplicationException("No existe coche con matrícula: " + matricula);
            }

            // 2. Verificar que el coche NO esté vendido
            if (coche.getVenta() != null) {
                throw new ApplicationException("El coche con matrícula " + matricula + " ya está vendido");
            }

            // 3. Buscar concesionario por ID
            Concesionario concesionario = conexion.find(Concesionario.class, idConcesionario);
            if (concesionario == null) {
                throw new ApplicationException("No existe concesionario con ID: " + idConcesionario);
            }

            // 4. Crear nuevo propietario
            Propietario propietario = new Propietario(dni, nombrePropietario);
            conexion.persist(propietario);

            // 5. Crear la venta con fecha actual
            Venta venta = new Venta(LocalDate.now(), precioFinal, coche, propietario, concesionario);
            conexion.persist(venta);

            conexion.getTransaction().commit();
            System.out.println(" VENTA REALIZADA CORRECTAMENTE ");
            System.out.println(" Coche: " + matricula + " | Comprador: " + nombrePropietario + " | Precio: " + precioFinal + " €");

        } catch (ApplicationException e) {
            if (conexion != null && conexion.getTransaction().isActive()) {
                conexion.getTransaction().rollback();
            }
            throw e;
        } catch (Exception e) {
            if (conexion != null && conexion.getTransaction().isActive()) {
                conexion.getTransaction().rollback();
            }
            throw new ApplicationException("ERROR AL REALIZAR VENTA: " + e.getMessage());
        }

    }
}
