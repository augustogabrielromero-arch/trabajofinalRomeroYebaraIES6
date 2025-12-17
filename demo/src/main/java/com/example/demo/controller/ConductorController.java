package com.example.demo.controller;


import com.example.demo.model.Conductor;
import com.example.demo.service.ConductorService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; // CAMBIADO a @Controller
import org.springframework.ui.Model; // IMPORTADO para pasar datos a la vista
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ConductorController {
     private final ConductorService conductorService;

    // Inyección por constructor
    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    // --- MÉTODOS MVC ---

    // 1. LISTAR USUARIOS ACTIVOS
    @GetMapping("/listarConductor")
    public String listarConductorActivos(Model model) {
        List<Conductor> conductor = conductorService.obtenerTodosConductorActivos();
        model.addAttribute("conductor", conductor);
        return "listaConductor";
    }

    // 2. MOSTRAR FORMULARIO NUEVO USUARIO
    @GetMapping("/nuevoConductor")
    public String mostrarFormularioRegistroConductor(Model model) {
        model.addAttribute("conductor", new Conductor());
        return "formularioConductor";
    }

    // 3. GUARDAR NUEVO USUARIO
    
    @PostMapping("/guardarConductor")
    public String guardarConductor(@ModelAttribute Conductor conductor) {
        conductorService.guardarConductor(conductor);
        return "redirect:/listarConductor";
    }

    // 4. DETALLE USUARIO POR ID
    @GetMapping("/detalleConductor/{id}")
    public String verDetalleConductor(@PathVariable("id") Integer id, Model model) {

        Conductor conductor = conductorService.obtenerConductorPorId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Conductor no encontrado con ID: " + id));

        model.addAttribute("conductor", conductor);
        return "detalleConductor";
    }

    // 5. ELIMINAR USUARIO (BORRADO LÓGICO)
    @GetMapping("/eliminarConductor/{id}")
    public String eliminarConductorLogico(@PathVariable("id") Integer id) {

        conductorService.eliminarConductorLogico(id);

        return "redirect:/listarConductor";
    }

    // 6. FORMULARIO PARA EDITAR USUARIO
    @GetMapping("/editarConductor/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {

        Conductor conductor = conductorService.obtenerConductorPorId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Conductor no encontrado para editar con ID: " + id));

        model.addAttribute("conductor", conductor);
        return "formularioConductor";
    }

    // 7. PROCESAR ACTUALIZACIÓN
    @PostMapping("/actualizarConductor/{id}")
    public String actualizarConductor(@PathVariable("id") Integer id, 
                                    @ModelAttribute Conductor conductorActualizado) {

        conductorActualizado.setConductorId(id);

        Conductor conductorResultado = conductorService.actualizarConductor(id, conductorActualizado);

        return "redirect:/listarConductor";
    }

}