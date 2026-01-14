package org.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reparacion {
    private Long id;
    private LocalDate fecha;
    private BigDecimal coste;
    private String descripcion;

    //RELACIONES
    private Coche coche; // Muchos a uno -> N:1
    private Mecanico mecanico; // Muchos a uno -> N:1


    // CONSTRUCTORES ----------------------------------------------------------->


    public Reparacion() {
    }

    public Reparacion(LocalDate fecha, BigDecimal coste, String descripcion, Coche coche, Mecanico mecanico) {
        this.fecha = fecha;
        this.coste = coste;
        this.descripcion = descripcion;
        this.coche = coche;
        this.mecanico = mecanico;
    }


    // GETTERS ----------------------------------------------------------->

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public BigDecimal getCoste() {
        return coste;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Coche getCoche() {
        return coche;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }


    // SETTERS ----------------------------------------------------------->


    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setCoste(BigDecimal coste) {
        this.coste = coste;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    // TO - STRING() ----------------------------------------------------------->

    @Override
    public String toString() {
        return "Reparación{" +
                "fecha=" + fecha +
                ", coste=" + coste +
                ", descripcion='" + descripcion + '\'' +
                ", mecanico=" + mecanico.getNombre() +
                '}';
    }

}
