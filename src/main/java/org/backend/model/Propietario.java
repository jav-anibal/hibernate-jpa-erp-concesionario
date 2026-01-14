package org.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "propietarios")
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 9, nullable = false)
    private String dni;
    @Column(length = 20, nullable = false)
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

    // EQUALS & HASHCODE ----------------------------------------------------------->


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Propietario that)) return false;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}
