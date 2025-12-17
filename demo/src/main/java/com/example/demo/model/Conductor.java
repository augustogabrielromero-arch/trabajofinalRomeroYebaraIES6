package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.*;
@Entity
public class Conductor {
    
 // Bloque de los atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer conductorId;
    @Column
    private String dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String licencia;
    @Column
    private Boolean estadoConductor = true;

// Relación 1:1 con Vehiculo: un conductor tienen un vehiculo.
    @OneToOne
    @JoinColumn(name = "vehiculoId") 
    private Vehiculo vehiculo;

    // Constructores
    public Conductor() {
    }

    public Conductor(Integer conductorId, String dni, String nombre, String apellido, String licencia, Boolean estadoConductor) {
        this.conductorId = conductorId;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.licencia = licencia;
        this.estadoConductor = true;
    }

    // Métodos accesores get y setter
     public Integer getConductorId() {
        return conductorId;
    }

    public void setConductorId(Integer conductorId) {
        this.conductorId = conductorId;
    }

     public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public Boolean isEstadoConductor() {
        return estadoConductor;
    }

    public void setEstadoConductor(Boolean estadoConductor) {
        this.estadoConductor = estadoConductor;
    }

}
