package org.backend.model;

import java.math.BigDecimal;

public class Equipamiento {
    private Long id;
    private String nombre;
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
