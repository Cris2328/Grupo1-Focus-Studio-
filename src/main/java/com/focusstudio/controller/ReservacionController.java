package com.focusstudio.controller;

import com.focusstudio.domain.Servicio;
import com.focusstudio.repository.ServicioRepository;
import com.focusstudio.service.DisponibilidadService;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import com.focusstudio.domain.Reservacion;
import com.focusstudio.service.ReservacionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservaciones")
public class ReservacionController {

    private final ServicioRepository servicioRepository;
    private final DisponibilidadService disponibilidadService;
    private final ReservacionService reservacionService;

public ReservacionController(
        ServicioRepository servicioRepository,
        DisponibilidadService disponibilidadService,
        ReservacionService reservacionService) {

    this.servicioRepository = servicioRepository;
    this.disponibilidadService = disponibilidadService;
    this.reservacionService = reservacionService;
}

    @GetMapping("/nueva")
    public String mostrarFormulario(
            @RequestParam Long servicioId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha,
            @RequestParam
            @DateTimeFormat(pattern = "HH:mm")
            LocalTime hora,
            Model model) {

        Servicio servicio = servicioRepository.findById(servicioId)
                .filter(Servicio::isActivo)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "El servicio no está disponible"));

        boolean horarioDisponible = disponibilidadService
                .obtenerHorariosDisponibles(
                        fecha,
                        servicio.getDuracionMinutos())
                .contains(hora);

        if (!horarioDisponible) {
            model.addAttribute(
                    "error",
                    "El horario seleccionado ya no está disponible. "
                    + "Por favor, elegí otro.");

            model.addAttribute(
                    "servicios",
                    servicioRepository.findByActivoTrue());

            return "disponibilidad";
        }

        model.addAttribute("servicio", servicio);
        model.addAttribute("fecha", fecha);
        model.addAttribute("hora", hora);

        return "reservacion-formulario";
    }
    
    @PostMapping("/guardar")
public String guardarReservacion(
        @RequestParam Long servicioId,
        @RequestParam
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate fecha,
        @RequestParam
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime hora,
        @RequestParam String ubicacion,
        RedirectAttributes redirectAttributes) {

    try {

        Reservacion reservacion =
                reservacionService.crearReservacion(
                        servicioId,
                        fecha,
                        hora,
                        ubicacion);

        return "redirect:/reservaciones/confirmacion/"
                + reservacion.getCodigo();

    } catch (IllegalArgumentException e) {

        redirectAttributes.addFlashAttribute(
                "error", e.getMessage());

        redirectAttributes.addAttribute(
                "servicioId", servicioId);

        redirectAttributes.addAttribute(
                "fecha", fecha);

        redirectAttributes.addAttribute(
                "hora", hora);

        return "redirect:/reservaciones/nueva";
    }
}
}