package com.focusstudio.service;

import com.focusstudio.domain.Reservacion;
import com.focusstudio.domain.Servicio;
import com.focusstudio.repository.ReservacionRepository;
import com.focusstudio.repository.ServicioRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservacionService {

    private final ReservacionRepository reservacionRepository;
    private final ServicioRepository servicioRepository;
    private final DisponibilidadService disponibilidadService;
    private final JdbcTemplate jdbcTemplate;

    public ReservacionService(
            ReservacionRepository reservacionRepository,
            ServicioRepository servicioRepository,
            DisponibilidadService disponibilidadService,
            JdbcTemplate jdbcTemplate) {

        this.reservacionRepository = reservacionRepository;
        this.servicioRepository = servicioRepository;
        this.disponibilidadService = disponibilidadService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Reservacion crearReservacion(
            Long servicioId,
            LocalDate fecha,
            LocalTime hora,
            String ubicacion) {

        // Validar campos obligatorios
        if (servicioId == null || fecha == null
                || hora == null || ubicacion == null
                || ubicacion.isBlank()) {

            throw new IllegalArgumentException(
                    "Todos los campos son obligatorios.");
        }

        if (ubicacion.trim().length() > 255) {
            throw new IllegalArgumentException(
                    "La ubicación no puede superar 255 caracteres.");
        }

        // Bloquear la agenda durante la reservación
        Integer control = jdbcTemplate.queryForObject(
                "SELECT id FROM fs_control_reservacion "
                + "WHERE id = 1 FOR UPDATE",
                Integer.class
        );

        if (control == null) {
            throw new IllegalStateException(
                    "No se encontró el control de reservaciones.");
        }

        // Buscar el servicio seleccionado
        Servicio servicio = servicioRepository.findById(servicioId)
                .filter(Servicio::isActivo)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El servicio seleccionado no está disponible."));

        if (servicio.getDuracionMinutos() == null
                || servicio.getDuracionMinutos() <= 0) {

            throw new IllegalArgumentException(
                    "El servicio tiene una duración inválida.");
        }

        // Comprobar nuevamente la disponibilidad
        boolean disponible = disponibilidadService
                .obtenerHorariosDisponibles(
                        fecha, servicio.getDuracionMinutos())
                .contains(hora);

        if (!disponible) {
            throw new IllegalArgumentException(
                    "El horario ya no está disponible. Elegí otro.");
        }

        // Crear la reservación
        Reservacion reservacion = new Reservacion();

        reservacion.setServicio(servicio);
        reservacion.setFecha(fecha);
        reservacion.setHoraInicio(hora);
        reservacion.setDuracionMinutos(
                servicio.getDuracionMinutos());
        reservacion.setUbicacion(ubicacion.trim());

        reservacion.setCodigo(
                "FS-" + UUID.randomUUID().toString()
                        .substring(0, 8).toUpperCase());

        reservacion.setEstado("CONFIRMADA");

        // Guardar en MySQL
        return reservacionRepository.save(reservacion);
    }
}