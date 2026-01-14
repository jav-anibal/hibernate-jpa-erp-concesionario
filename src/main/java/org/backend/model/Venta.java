package org.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venta {
    private Long id;
    private LocalDate fecha;
    private BigDecimal precioFinal;

    //RELACIONES
    private Coche coche; // Uno a uno -> 1:1
    private Propietario propietario; // Muchos a uno -> N:1
    private Concesionario concesionario; // Muchos a uno -> N:1

    // CONSTRUCTORES ----------------------------------------------------------->

    public Venta() {
    }

    public Venta(LocalDate fecha, BigDecimal precioFinal, Coche coche, Propietario propietario, Concesionario concesionario) {
        this.fecha = fecha;
        this.precioFinal = precioFinal;
        this.coche = coche;
        this.propietario = propietario;
        this.concesionario = concesionario;
    }

    // GETTERS ----------------------------------------------------------->

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public Coche getCoche() {
        return coche;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }


    // SETTERS ----------------------------------------------------------->


    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

    // TO - STRING ----------------------------------------------------------->


    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", precioFinal=" + precioFinal +
                ", coche=" + coche.getMatricula() + coche.getMarca() +
                ", propietario=" + propietario.getNombre() +
                ", concesionario=" + concesionario.getNombre() +
                '}';
    }
}
