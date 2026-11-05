
package com.focusstudio.repository;

import com.focusstudio.domain.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository
        extends JpaRepository<Servicio, Long> {

    // HU-03: Consultar únicamente los servicios activos
    List<Servicio> findByActivoTrue();
}