-- Focus Studio
-- Horarios de ejemplo para HU-04 y HU-05
-- Ejecutar después de iniciar Spring Boot
-- para que Hibernate haya creado las tablas.

USE focus_studio;

-- Horarios disponibles para el fotógrafo
-- Fecha de ejemplo: 21 de noviembre de 2026

INSERT INTO fs_horario_disponible
(fecha, hora_inicio, hora_fin, activo)
VALUES
('2026-11-21', '09:00:00', '12:00:00', 1),
('2026-11-21', '14:00:00', '17:00:00', 1);

-- Bloqueo de ejemplo: fotógrafo no disponible
-- entre las 10:00 y las 11:00

INSERT INTO fs_bloqueo_horario
(fecha, hora_inicio, hora_fin, motivo, activo)
VALUES
('2026-11-21', '10:00:00', '11:00:00',
 'Horario bloqueado de ejemplo', 1);