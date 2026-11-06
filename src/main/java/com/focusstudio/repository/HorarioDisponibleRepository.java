package com.focusstudio.repository;

import com.focusstudio.domain.HorarioDisponible;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioDisponibleRepository
        extends JpaRepository<HorarioDisponible, Long> {

    List<HorarioDisponible> findByFechaAndActivoTrue(
            LocalDate fecha);
}