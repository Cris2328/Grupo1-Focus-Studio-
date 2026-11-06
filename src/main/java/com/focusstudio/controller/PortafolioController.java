package com.focusstudio.controller;

import com.focusstudio.service.FotografiaService;
import com.focusstudio.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PortafolioController {

    private final FotografiaService fotografiaService;
    private final CategoriaRepository categoriaRepository;

    public PortafolioController(
            FotografiaService fotografiaService,
            CategoriaRepository categoriaRepository) {

        this.fotografiaService = fotografiaService;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/portafolio")
    public String mostrarPortafolio(
            @RequestParam(required = false) Long categoriaId,
            Model model) {

        if (categoriaId == null) {
            model.addAttribute(
                    "fotografias",
                    fotografiaService.obtenerFotografiasActivas()
            );
        } else {
            model.addAttribute(
                    "fotografias",
                    fotografiaService.obtenerFotografiasPorCategoria(categoriaId)
            );
        }

        model.addAttribute(
                "categorias",
                categoriaRepository.findAll()
                        .stream()
                        .filter(categoria -> categoria.isActiva())
                        .toList()
        );

        model.addAttribute("categoriaSeleccionada", categoriaId);

        return "portafolio";
    }
}