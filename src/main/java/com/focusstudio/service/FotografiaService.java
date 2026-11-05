package com.focusstudio.service;

import com.focusstudio.domain.Fotografia;
import com.focusstudio.repository.FotografiaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FotografiaService {

    private final FotografiaRepository fotografiaRepository;

    public FotografiaService(
            FotografiaRepository fotografiaRepository) {
        this.fotografiaRepository = fotografiaRepository;
    }

    // HU-01: Obtener todas las fotografías activas
    @Transactional(readOnly = true)
    public List<Fotografia> obtenerFotografiasActivas() {
        return fotografiaRepository.findByActivaTrue();
    }

    // HU-02: Obtener fotografías activas por categoría
    @Transactional(readOnly = true)
    public List<Fotografia> obtenerFotografiasPorCategoria(Long categoriaId) {
        return fotografiaRepository
                .findByActivaTrueAndCategoriaId(categoriaId);
    }
}