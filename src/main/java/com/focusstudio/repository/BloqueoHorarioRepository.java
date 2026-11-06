package com.focusstudio.repository;

import com.focusstudio.domain.BloqueoHorario;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueoHorarioRepository
        extends JpaRepository<BloqueoHorario, Long> {

    List<BloqueoHorario> findByFechaAndActivoTrue(
            LocalDate fecha);
}