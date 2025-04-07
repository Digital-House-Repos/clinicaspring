# Proyecto Clínica Dental

Este proyecto tiene como objetivo informatizar la operación de una clínica dental. Se ha desarrollado un sistema para la
gestión de los pacientes, odontólogos, domicilios y turnos. Los usuarios podrán dar de alta, buscar y listar a los
pacientes, así como gestionar los turnos con los odontólogos disponibles.

## Descripción

El sistema está diseñado para permitir la administración de la clínica de manera eficiente, con funcionalidades para
gestionar los siguientes elementos:

- **Pacientes**: Alta de nuevos pacientes, búsqueda y listado de los mismos.
- **Odontólogos**: Alta de odontólogos, asignación de turnos y consultas.
- **Turnos**: Gestión de turnos, asignación de odontólogos a pacientes en fechas y horas específicas.
- **Domicilios**: Cada paciente tiene un domicilio relacionado, con su respectiva dirección.

## Requisitos Previos

- **Docker**: Asegúrate de tener Docker instalado en tu máquina.
- **Docker Compose**: Necesitarás Docker Compose para facilitar la administración de los contenedores.

## Ejecución del proyecto en Docker

Para instalar y ejecutar el proyecto en tu máquina local, sigue los siguientes pasos:

1. Clona el repositorio desde GitHub:
   ```bash
   git clone https://github.com/Digital-House-Repos/clinicaspring
   ```

2. Navega al directorio del proyecto:
   ```bash
   cd proyecto-clinica-dental
   ```

3. Construye y ejecuta el contenedor Docker:
   ```bash
    docker-compose up --build -d # windows
    docker compose up --build -d # linux
   ```

4. Accede a la aplicación en tu navegador web en el puerto 8080:
    - [http://localhost:8080](http://localhost:8080)

5. Si deseas acceder a la base de datos H2:
    - [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

    - Configuración de conexión:
        - JDBC URL: `jdbc:h2:mem:clinica`
        - User Name: `sa`
        - Password: (dejar en blanco)

   > **Nota**: Si el puerto 8080 ya está en uso, puedes cambiarlo en el archivo `docker-compose.yml`.

## Acceso y Funcionalidades

El sistema tiene un login para dos tipos de usuarios:

- Admin: Acceso completo para gestionar pacientes, odontólogos y turnos.

    - Usuario: **admin**
    - Contraseña: **admin**

- User: Acceso limitado para ver la información.
    - Usuario: **user**
    - Contraseña: **admin**

## Contribuciones

Este proyecto fue desarrollado por:

- Jean Franco Tineo
- Claudia Heredia