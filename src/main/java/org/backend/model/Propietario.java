package org.backend.model;

public class Propietario {
    private Long id;
    private String dni;
    private String nombre;

    // CONSTRUCTORES ----------------------------------------------------------->

    public Propietario() {
    }

    public Propietario(String dni, String nombre) {

        this.dni = dni;
        this.nombre = nombre;
    }

    // GETTERS ----------------------------------------------------------->

    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }


    // SETTERS ----------------------------------------------------------->


    public void setId(Long id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
