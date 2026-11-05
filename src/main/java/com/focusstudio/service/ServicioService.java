package com.focusstudio.service;

import com.focusstudio.domain.Servicio;
import com.focusstudio.repository.ServicioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(
            ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    // HU-03: Obtener únicamente los servicios activos
    @Transactional(readOnly = true)
    public List<Servicio> obtenerServiciosActivos() {
        return servicioRepository.findByActivoTrue();
    }
}