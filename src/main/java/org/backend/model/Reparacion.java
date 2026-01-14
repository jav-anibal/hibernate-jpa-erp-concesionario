package org.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reparaciones")
public class Reparacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate fecha;
    @Column
    private BigDecimal coste;
    @Column(length = 255, nullable = false)
    private String descripcion;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "coche_matricula", nullable = false)
    private Coche coche; // Muchos a uno -> N:1
    @ManyToOne
    @JoinColumn(name = "mecanico_id", nullable = false)
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



}
