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
    @GetMapping("/nuevoUsuario")
    public String mostrarFormularioRegistroUsuario(Model model) {
        model.addAttribute("conductor", new Conductor());
        return "formularioUsuario";
    }

    // 3. GUARDAR NUEVO USUARIO
    
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Conductor conductor) {
        conductorService.guardarConductor(conductor);
        return "redirect:/listarCondcutor";
    }

    // 4. DETALLE USUARIO POR ID
    @GetMapping("/detalleUsuario/{id}")
    public String verDetalleUsuario(@PathVariable("id") Integer id, Model model) {

        Conductor conductor = conductorService.obtenerConductorPorId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Usuario no encontrado con ID: " + id));

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
    @GetMapping("/editarUsuario/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {

        Conductor conductor = conductorService.obtenerConductorPorId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Usuario no encontrado para editar con ID: " + id));

        model.addAttribute("conductor", conductor);
        return "formularioConductor";
    }

    // 7. PROCESAR ACTUALIZACIÓN
    @PostMapping("/actualizarConductor/{id}")
    public String actualizarUsuario(@PathVariable("id") Integer id, 
                                    @ModelAttribute Conductor conductorActualizado) {

        conductorActualizado.setConductorId(id);

        Conductor conductorResultado = conductorService.actualizarConductor(id, conductorActualizado);

        return "redirect:/listarConductor";
    }

}