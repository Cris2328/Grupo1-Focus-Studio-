package com.focusstudio.controller;
import com.focusstudio.dto.RegistroForm;
import com.focusstudio.repository.ReservaReferenciaRepository;
import com.focusstudio.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller public class PublicoController {
 private final UsuarioService usuarios;private final ReservaReferenciaRepository reservas;
 public PublicoController(UsuarioService u,ReservaReferenciaRepository r){usuarios=u;reservas=r;}
 @GetMapping("/") public String inicio(){return "inicio";}
 @GetMapping("/registro") public String registro(Model m){m.addAttribute("form",new RegistroForm());return "registro";}
 @PostMapping("/registro") public String registrar(@Valid @ModelAttribute("form") RegistroForm form,BindingResult errors,Model m){if(errors.hasErrors())return "registro";try{usuarios.registrar(form);}catch(IllegalArgumentException ex){m.addAttribute("error",ex.getMessage());return "registro";}return "redirect:/registro?ok";}
 // HU-06: solo se muestra una reserva existente; la creación corresponde a HU-05 (Nicole).
 @GetMapping("/reservaciones/confirmacion/{codigo}") public String confirmar(@PathVariable String codigo,Model m){var reserva=reservas.findByCodigo(codigo).orElse(null);if(reserva==null)return "reserva-no-encontrada";m.addAttribute("reserva",reserva);return "confirmacion";}
}
