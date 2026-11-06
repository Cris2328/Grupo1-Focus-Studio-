-- Focus Studio
-- Configuración de control de reservaciones
-- HU-04 y HU-05

USE focus_studio;

-- Tabla utilizada para evitar conflictos
-- durante la creación simultánea de reservaciones.

CREATE TABLE IF NOT EXISTS fs_control_reservacion (
    id BIGINT PRIMARY KEY
) ENGINE = InnoDB;

-- Crear el registro de control si no existe.

INSERT INTO fs_control_reservacion (id)
SELECT 1
WHERE NOT EXISTS (
    SELECT 1
    FROM fs_control_reservacion
    WHERE id = 1
);

-- Verificar el registro de control.

SELECT *
FROM fs_control_reservacion;