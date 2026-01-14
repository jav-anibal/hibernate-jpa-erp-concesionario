package org.backend.service;

import jakarta.persistence.EntityManager;
import org.backend.model.*;

import java.math.BigDecimal;

public class SeederService {

    public static void seed() {

        try {
            EntityManager em = GestorEntityManager.getEntityManager();
            em.getTransaction().begin();

            // Borrar datos existentes
            em.createQuery("DELETE FROM Venta").executeUpdate();
            em.createQuery("DELETE FROM Coche").executeUpdate();
            em.createQuery("DELETE FROM Equipamiento").executeUpdate();
            em.createQuery("DELETE FROM Propietario").executeUpdate();
            em.createQuery("DELETE FROM Mecanico").executeUpdate();
            em.createQuery("DELETE FROM Concesionario").executeUpdate();

            // Creando concesionario
            Concesionario c1 = new Concesionario("AutosMiravent", "Avenida del Carnaval");
            em.persist(c1);

            // Creando varios coches
            Coche coche1 = new Coche("8910JCY", "Nissan", "Pulsar", BigDecimal.valueOf(20000), c1);
            Coche coche2 = new Coche("1234ABC", "Toyota", "Corolla", BigDecimal.valueOf(25000), c1);
            Coche coche3 = new Coche("5678DEF", "Ford", "Focus", BigDecimal.valueOf(18000), c1);
            em.persist(coche1);
            em.persist(coche2);
            em.persist(coche3);

            // Creando varios equipamientos
            Equipamiento e1 = new Equipamiento("Climatizador", BigDecimal.valueOf(1000));
            em.persist(e1);

            // Asignando equipamiento al coche
            coche1.getEquipamientos().add(e1);

            // Creando varios propietarios
            Propietario p1 = new Propietario("12345678A", "Anibal Solano");
            Propietario p2 = new Propietario("87654321B", "Maria Garcia");
            Propietario p3 = new Propietario("11223344C", "Carlos Lopez");
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);

            // Creando varios mecanicos
            Mecanico m1 = new Mecanico("Juan Perez");
            Mecanico m2 = new Mecanico("Pedro Martinez");
            Mecanico m3 = new Mecanico("Luis Sanchez");
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);

            // Crear venta
            // Venta v1 = new Venta(LocalDate.now(), BigDecimal.valueOf(21000), coche1, p1, c1);
            // em.persist(v1);

            em.getTransaction().commit();
            System.out.println("Se han cargado datos por defecto en la tablas ");

        } catch (Exception ex) {

            try {
                EntityManager em = GestorEntityManager.getEntityManager();
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } catch (Exception e) {
                System.out.println("Error al persistir datos " + ex.getMessage());
            }

        }

    }
}

