package org.backend.model;

public class Mecanico {
    private Long id;
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
