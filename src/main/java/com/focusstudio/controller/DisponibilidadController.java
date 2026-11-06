package com.focusstudio.controller;

import com.focusstudio.domain.Servicio;
import com.focusstudio.repository.ServicioRepository;
import com.focusstudio.service.DisponibilidadService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DisponibilidadController {

    private final DisponibilidadService disponibilidadService;
    private final ServicioRepository servicioRepository;

    public DisponibilidadController(
            DisponibilidadService disponibilidadService,
            ServicioRepository servicioRepository) {

        this.disponibilidadService = disponibilidadService;
        this.servicioRepository = servicioRepository;
    }

    @GetMapping("/disponibilidad")
    public String mostrarDisponibilidad(Model model) {

        model.addAttribute(
                "servicios",
                servicioRepository.findByActivoTrue());

        return "disponibilidad";
    }

    @GetMapping("/disponibilidad/horarios")
    @ResponseBody
    public List<LocalTime> consultarHorarios(
            @RequestParam Long servicioId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha) {

        Servicio servicio = servicioRepository.findById(servicioId)
                .filter(Servicio::isActivo)
                .orElseThrow(() ->
                        new org.springframework.web.server
                                .ResponseStatusException(
                                        org.springframework.http
                                                .HttpStatus.BAD_REQUEST,
                                        "Servicio no disponible"));

        return disponibilidadService.obtenerHorariosDisponibles(
                fecha,
                servicio.getDuracionMinutos());
    }
}