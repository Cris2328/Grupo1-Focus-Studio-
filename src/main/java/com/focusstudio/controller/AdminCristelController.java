package com.focusstudio.controller;
import com.focusstudio.dto.CategoriaForm;
import com.focusstudio.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/admin") public class AdminCristelController {
 private final UsuarioService usuarios;private final CategoriaService categorias;private final ResumenService resumen;
 public AdminCristelController(UsuarioService u,CategoriaService c,ResumenService r){usuarios=u;categorias=c;resumen=r;}
 @GetMapping public String panel(Model m){m.addAttribute("resumen",resumen);return "admin/resumen";}
 @GetMapping("/usuarios") public String usuarios(Model m){m.addAttribute("usuarios",usuarios.todos());return "admin/usuarios";}
 @PostMapping("/usuarios/{id}/estado") public String estadoUsuario(@PathVariable Long id,@RequestParam boolean activo){usuarios.cambiarEstado(id,activo);return "redirect:/admin/usuarios";}
 @GetMapping("/categorias") public String categorias(Model m){m.addAttribute("categorias",categorias.todas());return "admin/categorias";}
 @GetMapping("/categorias/nueva") public String nueva(Model m){m.addAttribute("form",new CategoriaForm());m.addAttribute("id",null);return "admin/categoria-form";}
 @GetMapping("/categorias/{id}/editar") public String editar(@PathVariable Long id,Model m){var c=categorias.buscar(id);var f=new CategoriaForm();f.setNombre(c.getNombre());f.setDescripcion(c.getDescripcion());m.addAttribute("form",f);m.addAttribute("id",id);return "admin/categoria-form";}
 @PostMapping("/categorias/guardar") public String guardar(@RequestParam(required=false) Long id,@Valid @ModelAttribute("form") CategoriaForm f,BindingResult errors,Model m){if(errors.hasErrors()){m.addAttribute("id",id);return "admin/categoria-form";}categorias.guardar(id,f);return "redirect:/admin/categorias";}
 @PostMapping("/categorias/{id}/estado") public String estadoCategoria(@PathVariable Long id,@RequestParam boolean activa){categorias.cambiarEstado(id,activa);return "redirect:/admin/categorias";}
}
