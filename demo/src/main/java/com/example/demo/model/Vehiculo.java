package com.example.demo.model;

import java.util.ArrayList;
import java.util.List; 
import jakarta.persistence.*;
@Entity
public class Vehiculo {
    
     // Bloque de los atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehiculoId;
    @Column
    private String patente;
    @Column
    private String marca;
    @Column
    private String modelo;
    @Column
    private Integer año;
    @Column
    private Boolean estadoVehiculo = true;

// Relación 1:N con Viaje (un vehículo tiene muchos viajes)
   
    @OneToMany(mappedBy = "vehiculo") // esta mapeando , buscando por vehiculo, vehiculo por vehiculo
    private List<Viaje> viajes = new ArrayList<>();

    // Relación 1:1 con Conductor
    @OneToOne
    @JoinColumn(name = "conductorId")
    private Conductor conductor;

    // Constructores
    public Vehiculo() {
    };

    public Vehiculo(Integer vehiculoId, String patente, String marca, String modelo, Integer año, Boolean estadoVehiculo) {
        this.vehiculoId = vehiculoId;
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.estadoVehiculo = true;
    };

    // Métodos accesores get y setter
     public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

     public String getPatente() {
        return patente;
    }
    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Boolean isEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(Boolean estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }
}
