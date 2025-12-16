package com.example.demo.controller;


import com.example.demo.model.Vehiculo;
import com.example.demo.service.VehiculoService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; // CAMBIADO a @Controller
import org.springframework.ui.Model; // IMPORTADO para pasar datos a la vista
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class VehiculoController {
     private final VehiculoService vehiculoService;

    // Inyección por constructor
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // --- MÉTODOS MVC ---

    // 1. LISTAR USUARIOS ACTIVOS
    @GetMapping("/listarVehiculos")
    public String listarVehiculosActivos(Model model) {
        List<Vehiculo> vehiculos = vehiculoService.obtenerTodosVehiculoActivos();
        model.addAttribute("vehiculo", vehiculos);
        return "listaVehiculo";
    }

    // 2. MOSTRAR FORMULARIO NUEVO USUARIO
    @GetMapping("/nuevoVehiculo")
    public String mostrarFormularioRegistroVehiculo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "formularioVehiculo";
    }

    // 3. GUARDAR NUEVO USUARIO
    
    @PostMapping("/guardarVehiculo")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoService.guardarVehiculo(vehiculo);
        return "redirect:/listarVehiculos";
    }

    // 4. DETALLE USUARIO POR ID
    @GetMapping("/detalleVehiculo/{id}")
    public String verDetalleVehiculo(@PathVariable("id") Integer id, Model model) {

        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Vehiculo no encontrado con ID: " + id));

        model.addAttribute("vehiculo", vehiculo);
        return "detalleVehiculo";
    }

    // 5. ELIMINAR USUARIO (BORRADO LÓGICO)
    @GetMapping("/eliminarVehiculo/{id}")
    public String eliminarVehiculoLogico(@PathVariable("id") Integer id) {

        vehiculoService.eliminarVehiculoLogico(id);

        return "redirect:/listarVehiculos";
    }

    // 6. FORMULARIO PARA EDITAR USUARIO
    @GetMapping("/editarVehiculo/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {

        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Vehiculo no encontrado para editar con ID: " + id));

        model.addAttribute("vehiculo", vehiculo);
        return "formularioVehiculo";
    }

    // 7. PROCESAR ACTUALIZACIÓN
    @PostMapping("/actualizarVehiculo/{id}")
    public String actualizarVehiculo(@PathVariable("id") Integer id, 
                                    @ModelAttribute Vehiculo vehiculoActualizado) {

        vehiculoActualizado.setVehiculoId(id);

        Vehiculo vehiculoResultado = vehiculoService.actualizarVehiculo(id, vehiculoActualizado);

        return "redirect:/listarVehiculos";
    }

}