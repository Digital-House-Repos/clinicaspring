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

## Estructura de Datos

El sistema utiliza una base de datos en memoria H2 para almacenar la información de los pacientes, odontólogos,
domicilios y turnos. Los modelos de datos incluyen:

- **Paciente**: Cada paciente tiene un ID único, apellido, nombre, DNI, fecha de ingreso y un domicilio asociado.
- **Domicilio**: Un domicilio tiene su propio ID, dirección, número, localidad y provincia.
- **Odontólogo**: Cada odontólogo tiene un ID único, matrícula, nombre y apellido.
- **Turno**: Un turno está asociado a un paciente y un odontólogo, y tiene una fecha y hora.

## Requerimientos

El sistema está desarrollado con las siguientes tecnologías y requisitos:

- **Base de datos**: H2 (base de datos en memoria).
- **ORM**: Hibernate para el mapeo objeto-relacional.
- **Spring Boot**: Framework principal utilizado para el desarrollo.
- **Spring Data JPA**: Utilizado para interactuar con la base de datos.
- **Gestión de excepciones**: Manejo centralizado de excepciones mediante un Global Exception Handler.
- **Inyección de dependencias**: Usando `@Autowired` para la inyección de dependencias.

## Instalación y Ejecución

Para instalar y ejecutar el proyecto en tu máquina local, sigue los siguientes pasos:

1. Clona el repositorio desde GitHub:
   ```bash
   git clone https://github.com/Digital-House-Repos/clinicaspring
   ```

2. Navega al directorio del proyecto:
   ```bash
   cd proyecto-clinica-dental
   ```

3. Construye el proyecto y ejecuta la aplicación con Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. Una vez ejecutado, abre un navegador y ve a [http://localhost:8080](http://localhost:8080) para acceder a la
   aplicación.

5. Si deseas acceder a la consola H2 para ver los datos almacenados puedes hacerlo
   en [http://localhost:8080/h2-console](http://localhost:8080/h2-console). Con los siguientes datos de conexión:
    - JDBC URL: `jdbc:h2:mem:clinica`
    - User Name: `sa`

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