package org.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate fecha;
    @Column
    private BigDecimal precioFinal;

    //RELACIONES
    @OneToOne
    @JoinColumn(name = "coche_matricula")
    private Coche coche; // Uno a uno -> 1:1

    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    private Propietario propietario; // Muchos a uno -> N:1

    @ManyToOne
    @JoinColumn(name = "concesionario_id",nullable = false)
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


}
