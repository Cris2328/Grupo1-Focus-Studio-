package com.focusstudio.controller;

import com.focusstudio.dto.RegistroForm;
import com.focusstudio.repository.ReservacionRepository;
import com.focusstudio.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PublicoController {

    private final UsuarioService usuarios;
    private final ReservacionRepository reservas;

    public PublicoController(
            UsuarioService usuarios,
            ReservacionRepository reservas) {

        this.usuarios = usuarios;
        this.reservas = reservas;
    }

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/registro")
    public String registro(Model m) {
        m.addAttribute("form", new RegistroForm());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(
            @Valid @ModelAttribute("form") RegistroForm form,
            BindingResult errors,
            Model m) {

        if (errors.hasErrors()) {
            return "registro";
        }

        try {
            usuarios.registrar(form);
        } catch (IllegalArgumentException ex) {
            m.addAttribute("error", ex.getMessage());
            return "registro";
        }

        return "redirect:/registro?ok";
    }

    // HU-06: mostrar la confirmación de una reservación existente
    @GetMapping("/reservaciones/confirmacion/{codigo}")
    public String confirmar(
            @PathVariable String codigo,
            Model m) {

        var reserva = reservas.findByCodigo(codigo)
                .orElse(null);

        if (reserva == null) {
            return "reserva-no-encontrada";
        }

        m.addAttribute("reserva", reserva);

        return "confirmacion";
    }
}