package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
@Entity
public class Usuario {
    // Bloque de los atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;
    @Column
    private String dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private LocalDate fechaNac;
    @Column
    private Boolean estadoUsuario = true;

// Relación 1:N con Viaje un usuario hace muchos viajes
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Viaje> viajes = new ArrayList<>();

    // Constructores
    public Usuario() {
    }

    public Usuario(Integer usuarioId, String dni, String nombre, String apellido, LocalDate fechaNac, Boolean estadoUsuario) {
        this.usuarioId = usuarioId;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.estadoUsuario = true;
    }

    // Métodos accesores get y setter
     public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
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

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Boolean isEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

}