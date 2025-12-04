package com.example.demo.model;

public class Vehículo {
    
     // Bloque de los atributos
    @Id
    @GeneratedValue(strategy = generationType.IDENTITY)
    private Integer vehiculoId;
    @Column
    private String patente;
    @Column
    private String marca;
    @Column
    private String modelo;
    @Column
    private Int año;
    @Column
    private Boolean estadoVehiculo = true;

    // Constructores
    public Vehiculo() {
    }

    public Vehiculo(Integer vehiculoId, String patente, String marca, String modelo, Int año, Boolean estadoVehiculo) {
        this.vehiculoId = vehiculoId;
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.estadoVehiculo = true;
    }

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

    public void setMarca(String Marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Int getAño() {
        return año;
    }

    public void setAño(Int año) {
        this.año = año;
    }

    public Boolean isEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(Boolean estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }
}
