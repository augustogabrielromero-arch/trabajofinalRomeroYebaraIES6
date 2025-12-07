package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
public class Viaje {
     // Bloque de los atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer viajeId;
    @Column
    private String destino;
    @Column
    private String estado;
    @Column
    private String distancia;
    @Column
    private Double precio;
    @Column
    private Boolean estadoViaje = true;

    // Constructores
    public Viaje() {
    }

    public Viaje(Integer viajeId, String destino, String estado, String distancia, Double precio, Boolean estadoViaje) {
        this.viajeId = viajeId;
        this.destino = destino;
        this.estado = estado;
        this.distancia = distancia;
        this.precio = precio;
        this.estadoViaje = true;
    }

    // Métodos accesores get y setter
     public Integer getViajeId() {
        return viajeId;
    }

    public void setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
    }

     public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean isEstadoViaje() {
        return estadoViaje;
    }

    public void setEstadoViaje(Boolean estadoViaje) {
        this.estadoViaje = estadoViaje;
    }
}
