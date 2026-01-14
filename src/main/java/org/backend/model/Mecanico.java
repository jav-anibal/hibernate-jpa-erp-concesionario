package org.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mecanicos")
public class Mecanico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String nombre;

    // CONSTRUCTORES ----------------------------------------------------------->
    public Mecanico() {
    }

    public Mecanico(String nombre) {
        this.nombre = nombre;
    }

    // GETTERS ----------------------------------------------------------->

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


    // SETTERS ----------------------------------------------------------->


    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
