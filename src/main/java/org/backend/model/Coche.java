package org.backend.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




public class Coche {


    private String matricula; // PK
    private String marca;
    private String modelo;
    private BigDecimal precioBase;

    // R E L A C I O N E S ------------------------------------------------------>
    // Un coche pertenece a un concesionario -> Relación N:1
    private Concesionario concesionario; // Referencia -> FK
    // Un coche puede tener muchos equipamientos -> Relación N:M
    private Set<Equipamiento> equipamientos = new HashSet<>();
    //Un coche puede ser reparado por muchos mecánicos -> Relación 1:N
    private List<Reparacion> reparaciones = new ArrayList<>();
    private Venta venta; // 1:1


    // CONSTRUCTORES ----------------------------------------------------------->

    public Coche() {
    }

    public Coche(String matricula, String marca, String modelo, BigDecimal precioBase, Concesionario concesionario) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.precioBase = precioBase;
        this.concesionario = concesionario;
    }

    // GETTERS ----------------------------------------------------------->


    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public Set<Equipamiento> getEquipamientos() {
        return equipamientos;
    }

    public List<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public Venta getVenta() {
        return venta;
    }


    // SETTERS ----------------------------------------------------------->


    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

    public void setEquipamientos(Set<Equipamiento> equipamientos) {
        this.equipamientos = equipamientos;
    }

    public void setReparaciones(List<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
