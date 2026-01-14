package org.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;


@Entity
@Table(name = "coches")
public class Coche {
    @Id
    @Column(length = 7, updatable = false) //-> la clave no puede cambiar una vez insertada
    private String matricula; // PK
    @Column(length = 32, nullable = false)
    private String marca;
    @Column(length = 32, nullable = false)
    private String modelo;
    @Column
    private BigDecimal precioBase;

    // RELACIONES ------------------------------------------------------>
    // Un coche pertenece a un concesionario
    @ManyToOne //-> Relación N:1
    @JoinColumn(name = "concesionario_id")
    private Concesionario concesionario; // Referencia -> FK
    // Un coche puede tener muchos equipamientos

    @ManyToMany//-> Relación N:M
    @JoinTable(
            name = "coche_equipamiento",
            joinColumns = @JoinColumn(name = "coche_matricula"),
            inverseJoinColumns = @JoinColumn(name = "equipamiento_id")
    )//-> Representa la creación de una nueva tabla,
    // ya que una relación muchos-a-muchos,
    // las bases de datos relacionales
    // no pueden representarse directamente
    // con solo dos tablas (necesitan una tabla intermedia para unirlas)
    private Set<Equipamiento> equipamientos = new HashSet<>();
    //Un coche puede ser reparado por muchos mecánicos

    @OneToMany(mappedBy = "coche") //-> Relación 1:N - mappedBY: apunta al atributo que tiene la FK
    private List<Reparacion> reparaciones = new ArrayList<>();

    @OneToOne(mappedBy = "coche") // -> 1:1
    private Venta venta;


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

    // EQUALS & HASHCODE ----------------------------------------------------------->

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coche)) return false;
        Coche coche = (Coche) o;
        return Objects.equals(matricula, coche.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

}
