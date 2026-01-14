package org.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "equipamientos")
public class Equipamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40, nullable = false)
    private String nombre;
    @Column
    private BigDecimal precioUnit;


    // CONSTRUCTORES ----------------------------------------------------------->

    public Equipamiento() {
    }

    public Equipamiento(String nombre, BigDecimal precioUnit) {
        this.nombre = nombre;
        this.precioUnit = precioUnit;
    }

    // GETTERS ----------------------------------------------------------->


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecioUnit() {
        return precioUnit;
    }

    // SETTERS ----------------------------------------------------------->


    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioUnit(BigDecimal precioUnit) {
        this.precioUnit = precioUnit;
    }
}
