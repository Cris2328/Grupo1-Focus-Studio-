package com.focusstudio.service;

import com.focusstudio.domain.BloqueoHorario;
import com.focusstudio.domain.HorarioDisponible;
import com.focusstudio.domain.Reservacion;
import com.focusstudio.repository.BloqueoHorarioRepository;
import com.focusstudio.repository.HorarioDisponibleRepository;
import com.focusstudio.repository.ReservacionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisponibilidadService {

    private final HorarioDisponibleRepository horarioRepository;
    private final BloqueoHorarioRepository bloqueoRepository;
    private final ReservacionRepository reservacionRepository;

    public DisponibilidadService(
            HorarioDisponibleRepository horarioRepository,
            BloqueoHorarioRepository bloqueoRepository,
            ReservacionRepository reservacionRepository) {

        this.horarioRepository = horarioRepository;
        this.bloqueoRepository = bloqueoRepository;
        this.reservacionRepository = reservacionRepository;
    }

    @Transactional(readOnly = true)
    public List<LocalTime> obtenerHorariosDisponibles(
            LocalDate fecha, Integer duracionMinutos) {

        List<LocalTime> disponibles = new ArrayList<>();

        if (fecha == null || duracionMinutos == null
                || duracionMinutos <= 0
                || fecha.isBefore(LocalDate.now())) {
            return disponibles;
        }

        List<HorarioDisponible> horarios =
                horarioRepository.findByFechaAndActivoTrue(fecha);

        List<BloqueoHorario> bloqueos =
                bloqueoRepository.findByFechaAndActivoTrue(fecha);

        List<Reservacion> reservaciones =
                reservacionRepository.findByFechaAndEstadoNot(
                        fecha, "CANCELADA");

        // Evaluar horarios de inicio cada 30 minutos.
        for (HorarioDisponible horario : horarios) {

            LocalTime inicio = horario.getHoraInicio();
            LocalTime fin = horario.getHoraFin();

            if (inicio == null || fin == null
                    || !inicio.isBefore(fin)) {
                continue;
            }

            for (LocalTime candidato = inicio;
                    !candidato.plusMinutes(duracionMinutos)
                            .isAfter(fin);
                    candidato = candidato.plusMinutes(30)) {

                final LocalTime horaEvaluada = candidato;

                LocalTime finCandidato =
                        horaEvaluada.plusMinutes(duracionMinutos);

                // No permitir horarios que ya pasaron.
                if (LocalDateTime.of(fecha, horaEvaluada)
                        .isBefore(LocalDateTime.now())) {
                    continue;
                }

                boolean bloqueado = bloqueos.stream()
                        .anyMatch(b ->
                                hayConflicto(
                                        horaEvaluada,
                                        finCandidato,
                                        b.getHoraInicio(),
                                        b.getHoraFin()));

                boolean reservado = reservaciones.stream()
                        .anyMatch(r ->
                                hayConflicto(
                                        horaEvaluada,
                                        finCandidato,
                                        r.getHoraInicio(),
                                        r.getHoraInicio().plusMinutes(
                                                r.getDuracionMinutos())));

                if (!bloqueado && !reservado
                        && !disponibles.contains(horaEvaluada)) {

                    disponibles.add(horaEvaluada);
                }
            }
        }

        disponibles.sort(LocalTime::compareTo);

        return disponibles;
    }

    private boolean hayConflicto(
            LocalTime inicio1,
            LocalTime fin1,
            LocalTime inicio2,
            LocalTime fin2) {

        return inicio1.isBefore(fin2)
                && fin1.isAfter(inicio2);
    }
}