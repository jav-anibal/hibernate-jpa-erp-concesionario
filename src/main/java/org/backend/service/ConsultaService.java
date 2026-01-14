package org.backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.backend.model.*;

import java.math.BigDecimal;
import java.util.List;

public class ConsultaService {

    //==========================================================
    // PUNTO 5.1 -> STOCK DE CONCESIONARIO (coches no vendidos)
    // ==========================================================

    public static List<Coche> stockDeConcesionario(Long idConcesionario) {

        EntityManager conexion = null;
        try {
            conexion = GestorEntityManager.getEntityManager();

            // Verificar que el concesionario existe
            Concesionario concesionario = conexion.find(Concesionario.class, idConcesionario);
            if (concesionario == null) {
                throw new ApplicationException("No existe concesionario con ID: " + idConcesionario);
            }

            // JPQL: Coches del concesionario que NO tienen venta
            String jpql = "SELECT c FROM Coche c WHERE c.concesionario.id = :idConcesionario AND c.venta IS NULL";
            TypedQuery<Coche> query = conexion.createQuery(jpql, Coche.class);
            query.setParameter("idConcesionario", idConcesionario);

            return query.getResultList();

        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("ERROR EN CONSULTA STOCK: " + e.getMessage());
        }

    }

    //==========================================================
    // PUNTO 5.2 -> HISTORIAL DE MECÁNICO
    // ==========================================================

    public static List<Reparacion> historialDeMecanico(Long idMecanico) {

        EntityManager conexion = null;
        try {
            conexion = GestorEntityManager.getEntityManager();

            // Verificar que el mecánico existe
            Mecanico mecanico = conexion.find(Mecanico.class, idMecanico);
            if (mecanico == null) {
                throw new ApplicationException("No existe mecánico con ID: " + idMecanico);
            }

            // JPQL: Reparaciones del mecánico
            String jpql = "SELECT r FROM Reparacion r WHERE r.mecanico.id = :idMecanico ORDER BY r.fecha DESC";
            TypedQuery<Reparacion> query = conexion.createQuery(jpql, Reparacion.class);
            query.setParameter("idMecanico", idMecanico);

            return query.getResultList();

        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("ERROR EN CONSULTA HISTORIAL: " + e.getMessage());
        }

    }

    //==========================================================
    // PUNTO 5.3 -> VENTAS POR CONCESIONARIO
    // ==========================================================

    public static List<Venta> ventasPorConcesionario(Long idConcesionario) {

        EntityManager conexion = null;
        try {
            conexion = GestorEntityManager.getEntityManager();

            // Verificar que el concesionario existe
            Concesionario concesionario = conexion.find(Concesionario.class, idConcesionario);
            if (concesionario == null) {
                throw new ApplicationException("No existe concesionario con ID: " + idConcesionario);
            }

            // JPQL: Ventas del concesionario
            String jpql = "SELECT v FROM Venta v WHERE v.concesionario.id = :idConcesionario";
            TypedQuery<Venta> query = conexion.createQuery(jpql, Venta.class);
            query.setParameter("idConcesionario", idConcesionario);

            return query.getResultList();

        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("ERROR EN CONSULTA VENTAS: " + e.getMessage());
        }

    }

    public static BigDecimal totalRecaudadoPorConcesionario(Long idConcesionario) {

        EntityManager conexion = null;
        try {
            conexion = GestorEntityManager.getEntityManager();

            // JPQL: Suma de precios finales
            String jpql = "SELECT SUM(v.precioFinal) FROM Venta v WHERE v.concesionario.id = :idConcesionario";
            TypedQuery<BigDecimal> query = conexion.createQuery(jpql, BigDecimal.class);
            query.setParameter("idConcesionario", idConcesionario);

            BigDecimal total = query.getSingleResult();
            return total != null ? total : BigDecimal.ZERO;

        } catch (Exception e) {
            throw new ApplicationException("ERROR AL CALCULAR TOTAL: " + e.getMessage());
        }

    }

    //==========================================================
    // PUNTO 5.4 -> COSTE ACTUAL DE COCHE (solo si tiene propietario)
    // ==========================================================

    public static BigDecimal costeActualDeCoche(String matricula) {

        EntityManager conexion = null;
        try {
            conexion = GestorEntityManager.getEntityManager();

            // Buscar coche
            Coche coche = conexion.find(Coche.class, matricula);
            if (coche == null) {
                throw new ApplicationException("No existe coche con matrícula: " + matricula);
            }

            // Verificar que tiene propietario (está vendido)
            if (coche.getVenta() == null) {
                throw new ApplicationException("El coche " + matricula + " no tiene propietario (no está vendido)");
            }

            // 1. Precio de compra (precioFinal de la venta)
            BigDecimal precioCompra = coche.getVenta().getPrecioFinal();

            // 2. Suma de reparaciones (JPQL)
            String jpqlReparaciones = "SELECT COALESCE(SUM(r.coste), 0) FROM Reparacion r WHERE r.coche.matricula = :matricula";
            TypedQuery<BigDecimal> queryRep = conexion.createQuery(jpqlReparaciones, BigDecimal.class);
            queryRep.setParameter("matricula", matricula);
            BigDecimal totalReparaciones = queryRep.getSingleResult();

            // 3. Suma de extras (JPQL)
            String jpqlExtras = "SELECT COALESCE(SUM(e.precioUnit), 0) FROM Coche c JOIN c.equipamientos e WHERE c.matricula = :matricula";
            TypedQuery<BigDecimal> queryExtras = conexion.createQuery(jpqlExtras, BigDecimal.class);
            queryExtras.setParameter("matricula", matricula);
            BigDecimal totalExtras = queryExtras.getSingleResult();

            // Total = precioCompra + reparaciones + extras
            return precioCompra.add(totalReparaciones).add(totalExtras);

        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("ERROR AL CALCULAR COSTE: " + e.getMessage());
        }

    }
}
