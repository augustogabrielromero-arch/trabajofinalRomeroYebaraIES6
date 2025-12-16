package com.example.demo.controller;


import com.example.demo.model.Viaje;
import com.example.demo.service.ViajeService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; // CAMBIADO a @Controller
import org.springframework.ui.Model; // IMPORTADO para pasar datos a la vista
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ViajeController {
     private final ViajeService viajeService;

    // Inyección por constructor
    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    // --- MÉTODOS MVC ---

    // 1. LISTAR USUARIOS ACTIVOS
    @GetMapping("/listarViajes")
    public String listarViajesActivos(Model model) {
        List<Viaje> viajes = viajeService.obtenerTodosViajesActivos();
        model.addAttribute("viajes", viajes);
        return "listaViaje";
    }

    // 2. MOSTRAR FORMULARIO NUEVO USUARIO
    @GetMapping("/nuevoViaje")
    public String mostrarFormularioRegistroViaje(Model model) {
        model.addAttribute("viaje", new Viaje());
        return "formularioViaje";
    }

    // 3. GUARDAR NUEVO USUARIO
    
    @PostMapping("/guardarViaje")
    public String guardarViaje(@ModelAttribute Viaje viaje) {
        viajeService.guardarViaje(viaje);
        return "redirect:/listarViajes";
    }

    // 4. DETALLE USUARIO POR ID
    @GetMapping("/detalleViaje/{id}")
    public String verDetalleViaje(@PathVariable("id") Integer id, Model model) {

        Viaje viaje = viajeService.obtenerViajePorId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Viaje no encontrado con ID: " + id));

        model.addAttribute("viaje", viaje);
        return "detalleViaje";
    }

    // 5. ELIMINAR USUARIO (BORRADO LÓGICO)
    @GetMapping("/eliminarViaje/{id}")
    public String eliminarViajeLogico(@PathVariable("id") Integer id) {

        viajeService.eliminarViajeLogico(id);

        return "redirect:/listarViajes";
    }

    // 6. FORMULARIO PARA EDITAR USUARIO
    @GetMapping("/editarViaje/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {

        Viaje viaje = viajeService.obtenerViajePorId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Viaje no encontrado para editar con ID: " + id));

        model.addAttribute("viaje", viaje);
        return "formularioViaje";
    }

    // 7. PROCESAR ACTUALIZACIÓN
    @PostMapping("/actualizarViaje/{id}")
    public String actualizarViaje(@PathVariable("id") Integer id, 
                                    @ModelAttribute Viaje viajeActualizado) {

        viajeActualizado.setViajeId(id);

        Viaje viajeResultado = viajeService.actualizarViaje(id, viajeActualizado);

        return "redirect:/listarViajes";
    }

}
