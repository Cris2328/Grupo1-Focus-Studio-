package com.focusstudio.repository;

import com.focusstudio.domain.Reservacion;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReservacionRepository
        extends JpaRepository<Reservacion, Long> {

    List<Reservacion> findByFechaAndEstadoNot(
            LocalDate fecha, String estado);
    Optional<Reservacion> findByCodigo(String codigo);
}
