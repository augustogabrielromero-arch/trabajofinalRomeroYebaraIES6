package com.example.demo.controller;

import com.example.demo.model.Viaje;
import com.example.demo.service.ViajeService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VehiculoService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ViajeController {

    private final ViajeService viajeService;
    private final UsuarioService usuarioService;
    private final VehiculoService vehiculoService;

    // 🔑 Inyección por constructor (CORRECTA)
    public ViajeController(ViajeService viajeService,
                           UsuarioService usuarioService,
                           VehiculoService vehiculoService) {
        this.viajeService = viajeService;
        this.usuarioService = usuarioService;
        this.vehiculoService = vehiculoService;
    }

    // 1. LISTAR VIAJES ACTIVOS
    @GetMapping("/listarViajes")
    public String listarViajesActivos(Model model) {
        List<Viaje> viajes = viajeService.obtenerTodosViajesActivos();
        model.addAttribute("viajes", viajes);
        return "listaViaje";
    }

    // 2. MOSTRAR FORMULARIO NUEVO VIAJE
    @GetMapping("/nuevoViaje")
    public String mostrarFormularioRegistroViaje(Model model) {
        model.addAttribute("viaje", new Viaje());
        model.addAttribute("usuarios", usuarioService.obtenerTodosUsuariosActivos());
        model.addAttribute("vehiculos", vehiculoService.obtenerTodosVehiculoActivos());
        return "formularioViaje";
    }

    // 3. GUARDAR NUEVO VIAJE
    @PostMapping("/guardarViaje")
    public String guardarViaje(@ModelAttribute Viaje viaje) {
        viajeService.guardarViaje(viaje);
        return "redirect:/listarViajes";
    }

    // 4. DETALLE VIAJE
    @GetMapping("/detalleViaje/{id}")
    public String verDetalleViaje(@PathVariable Integer id, Model model) {

        Viaje viaje = viajeService.obtenerViajePorId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Viaje no encontrado"));

        model.addAttribute("viaje", viaje);
        return "detalleViaje";
    }

    // 5. ELIMINAR VIAJE (BORRADO LÓGICO)
    @GetMapping("/eliminarViaje/{id}")
    public String eliminarViajeLogico(@PathVariable Integer id) {
        viajeService.eliminarViajeLogico(id);
        return "redirect:/listarViajes";
    }

    // 6. FORMULARIO PARA EDITAR VIAJE
    @GetMapping("/editarViaje/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {

        Viaje viaje = viajeService.obtenerViajePorId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Viaje no encontrado"));

        model.addAttribute("viaje", viaje);
        model.addAttribute("usuarios", usuarioService.obtenerTodosUsuariosActivos());
        model.addAttribute("vehiculos", vehiculoService.obtenerTodosVehiculoActivos());

        return "formularioViaje";
    }

    // 7. PROCESAR ACTUALIZACIÓN
    @PostMapping("/actualizarViaje/{id}")
    public String actualizarViaje(@PathVariable Integer id,
                                  @ModelAttribute Viaje viajeActualizado) {

        viajeActualizado.setViajeId(id);
        viajeService.actualizarViaje(id, viajeActualizado);

        return "redirect:/listarViajes";
    }
}
