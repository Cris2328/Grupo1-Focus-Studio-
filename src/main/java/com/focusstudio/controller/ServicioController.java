package com.focusstudio.controller;

import com.focusstudio.service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    // HU-03: Mostrar los servicios y paquetes activos
    @GetMapping("/servicios")
    public String mostrarServicios(Model model) {

        model.addAttribute(
                "servicios",
                servicioService.obtenerServiciosActivos()
        );

        return "servicios";
    }
}