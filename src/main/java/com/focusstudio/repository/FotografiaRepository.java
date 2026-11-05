package com.focusstudio.repository;

import com.focusstudio.domain.Fotografia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotografiaRepository
        extends JpaRepository<Fotografia, Long> {

    // HU-01: Obtener todas las fotografías activas
    List<Fotografia> findByActivaTrue();

    // HU-02: Filtrar fotografías activas por categoría
    List<Fotografia> findByActivaTrueAndCategoriaId(Long categoriaId);
}