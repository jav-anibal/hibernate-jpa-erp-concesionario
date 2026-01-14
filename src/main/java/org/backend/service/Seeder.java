package org.backend.service;

import jakarta.persistence.EntityManager;
import org.backend.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Seeder {
    public static void seed() {
        EntityManager em = GestorEntityManager.getEntityManager();
        em.getTransaction().begin();

        // Crear concesionarios
        Concesionario c1 = new Concesionario("AutosMiravent", "Avenida del Carnaval");
        em.persist(c1);

        // Crear coches
        Coche coche1 = new Coche("8910JCY", "Nissan", "Pulsar", BigDecimal.valueOf(20000), c1);
        em.persist(coche1);

        // Crear equipamientos
        Equipamiento e1 = new Equipamiento("Climatizador", BigDecimal.valueOf(1000));
        em.persist(e1);

        // Asignar equipamiento al coche
        coche1.getEquipamientos().add(e1);

        // Crear propietario
        Propietario p1 = new Propietario("12345678A", "Anibal Solano");
        em.persist(p1);

        // Crear venta
        Venta v1 = new Venta(LocalDate.now(), BigDecimal.valueOf(21000), coche1, p1, c1);
        em.persist(v1);

        em.getTransaction().commit();
    }
}

