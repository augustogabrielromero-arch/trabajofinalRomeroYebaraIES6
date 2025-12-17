package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Viaje {

    // =========================
    // ATRIBUTOS
    // =========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer viajeId;

    @Column(nullable = false)
    private String destino;

    @Column
    private String estado;

    @Column
    private String distancia;

    @Column
    private Double precio;

    @Column
    private Boolean estadoViaje = true;

    // =========================
    // RELACIONES
    // =========================

    // Muchos viajes → un usuario
    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;

    // Muchos viajes → un vehículo
    @ManyToOne
    @JoinColumn(name = "vehiculoId", nullable = false)
    private Vehiculo vehiculo;

    // =========================
    // CONSTRUCTORES
    // =========================
    public Viaje() {
    }

    public Viaje(Integer viajeId, String destino, String estado, String distancia,
                 Double precio, Boolean estadoViaje, Usuario usuario, Vehiculo vehiculo) {
        this.viajeId = viajeId;
        this.destino = destino;
        this.estado = estado;
        this.distancia = distancia;
        this.precio = precio;
        this.estadoViaje = estadoViaje;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================
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

    // =========================
    // GETTERS Y SETTERS RELACIONES
    // =========================
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
