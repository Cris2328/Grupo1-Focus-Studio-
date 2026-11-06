# 📸 Grupo 1 — Focus Studio

El proyecto es desarrollado por los siguientes integrantes: 

Nicole Gómez Sánchez,
Cristel Vásquez Campos,
Edwin Clarke Espinoza,
Ashell Reyes Rocha

## 📋 Descripción del proyecto

**Focus Studio** es una aplicación web orientada a la gestión de servicios profesionales de fotografía y video. Su propósito es facilitar a los clientes la consulta de paquetes fotográficos, visualización de portafolios, disponibilidad de horarios y reservación de sesiones.

Además, proporciona herramientas administrativas para gestionar usuarios, servicios, categorías, reservaciones y disponibilidad del fotógrafo.

## 🎯 Objetivo general

Desarrollar una aplicación web funcional para la administración de servicios fotográficos, facilitando la interacción entre clientes, fotógrafos y administradores mediante una plataforma organizada, accesible y eficiente.


## ⚙️ Funcionalidades del sistema


- Visualización y filtrado del portafolio fotográfico.
- Consulta de servicios y paquetes.
- Consulta de fechas y horarios disponibles.
- Registro y gestión de reservaciones.
- Registro e inicio de sesión de usuarios.
- Administración de cuentas de usuarios.
- Gestión de servicios y categorías.
- Administración de disponibilidad del fotógrafo.
- Consulta de estadísticas y actividad del sistema.


## 📌 Estado actual del proyecto — Avance 2

Actualmente, **Focus Studio** se encuentra en su segundo avance de desarrollo.

Para esta entrega, el trabajo se concentra únicamente en las **primeras 10 historias de usuario (HU-01 a HU-10)**, asignadas a Nicole Gómez Sánchez y Cristel Vásquez Campos.

Las historias **HU-11 a HU-20** están contempladas dentro de la planificación general del proyecto, pero su implementación corresponde a etapas posteriores.

Las funcionalidades se considerarán completadas una vez que hayan sido desarrolladas, integradas y probadas correctamente.


## 👩‍💻 Distribución de historias de usuario

### Nicole Gómez Sánchez — HU-01 a HU-05

**Historias correspondientes al Avance 2.**

| ID | Funcionalidad |
|---|---|
| HU-01 | Visualización del portafolio fotográfico |
| HU-02 | Filtrado del portafolio por categorías |
| HU-03 | Consulta de servicios y paquetes |
| HU-04 | Consulta de disponibilidad |
| HU-05 | Reservación de servicios fotográficos |

### Cristel Vásquez Campos — HU-06 a HU-10

**Historias correspondientes al Avance 2.**


| ID | Funcionalidad |
|---|---|
| HU-06 | Confirmación de reservaciones |
| HU-07 | Registro de usuarios |
| HU-08 | Administración de cuentas de usuarios |
| HU-09 | Resumen de actividad del sistema |
| HU-10 | Gestión de categorías de servicios |

## 📅 Historias de usuario para próximas etapas

Las siguientes historias forman parte de la planificación general y no están incluidas en el alcance actual del Avance 2.

### Edwin Clarke Espinoza — HU-11 a HU-15

| ID | Funcionalidad |
|---|---|
| HU-11 | Actualización del estado de reservaciones |
| HU-12 | Inicio de sesión |
| HU-13 | Cancelación de reservaciones |
| HU-14 | Administración de servicios |
| HU-15 | Consulta de información de clientes |

### Ashell Reyes Rocha — HU-16 a HU-20

| ID | Funcionalidad |
|---|---|
| HU-16 | Gestión de servicios y paquetes |
| HU-17 | Administración de disponibilidad |
| HU-18 | Consulta de reservaciones |
| HU-19 | Administración del portafolio |
| HU-20 | Consulta del estado de reservaciones |

## 🗄️ Base de datos

Se utilizará **MySQL** para almacenar y administrar la información relacionada con:

- Usuarios y cuentas.
- Servicios y paquetes fotográficos.
- Categorías de servicios.
- Fotografías del portafolio.
- Reservaciones.
- Disponibilidad de horarios.



## 🚀 Desarrollo del Avance 2

El segundo avance contempla el desarrollo e integración de las primeras 10 historias de usuario.

Las actividades principales incluyen:

1. Desarrollo de las funcionalidades asignadas.
2. Creación de interfaces utilizando Thymeleaf y Bootstrap.
3. Integración de Spring Boot con MySQL.
4. Implementación de operaciones CRUD según los requerimientos.
5. Pruebas de funcionamiento de los módulos desarrollados.
6. Integración de los aportes en la rama principal del repositorio.

**Alcance actual:** HU-01 a HU-10.

**Pendiente para próximas etapas:** HU-11 a HU-20.




**Entrega:** Avance 2

**Historias de usuario del avance:** HU-01 a HU-10

🛠️ Requisitos e instalación

Para ejecutar Focus Studio en un entorno local, se necesitan las siguientes herramientas:

Java JDK 21.

Apache Maven.

MySQL Server.

MySQL Workbench (recomendado).

NetBeans IDE (recomendado).

Configuración de la base de datos

El proyecto utiliza una base de datos MySQL llamada focus_studio.

Iniciar el servidor MySQL.

Configurar las credenciales de conexión en src/main/resources/application.properties o mediante las variables de entorno DB_URL, DB_USER y DB_PASSWORD.

Ejecutar el proyecto para que Hibernate genere o actualice las tablas correspondientes, según la configuración de JPA.

Ejecutar en MySQL Workbench el script database/configuracion_reservaciones.sql, que crea y configura la tabla de control utilizada durante el registro de reservaciones.

Opcionalmente, se puede ejecutar database/horarios_ejemplo.sql para cargar horarios de prueba.

Ejecución de la aplicación

Clonar el repositorio de GitHub.

Abrir el proyecto Maven en NetBeans.

Verificar que MySQL esté funcionando.

Ejecutar el objetivo Maven spring-boot:run.

Abrir el navegador e ingresar a:

http://localhost:8081

Rutas principales

Ruta

Funcionalidad

/portafolio

Visualización del portafolio y filtros por categoría

/servicios

Consulta de servicios fotográficos

/disponibilidad

Consulta de fechas y horarios disponibles

/reservaciones/nueva

Formulario de reservación, con los parámetros del servicio y horario seleccionado

Flujo de reservación

El cliente consulta los servicios fotográficos, selecciona una fecha y un horario disponible y completa el formulario de reservación.

Antes de guardar la reservación, el sistema verifica nuevamente la disponibilidad para evitar conflictos con horarios bloqueados o reservaciones existentes.

Una vez registrada correctamente, el sistema genera un código de confirmación y muestra los detalles de la sesión reservada.
