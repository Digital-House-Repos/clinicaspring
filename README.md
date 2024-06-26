# Proyecto Clínica Dental

Una clínica dental necesita informatizar su operatoria. Por lo cual, se solicita un sistema para la gestión de sus
pacientes, donde puedan darlos de alta, buscarlos y listarlos.

## Integrantes

- Jean Franco Tineo
- Claudia Heredia

## Modelos

ID debe ser autoincremental

### Paciente

- pacienteId
- apellido
- nombre
- dni
- fechaIngreso
- domicilio

### Domicilio

- domicilioId
- calle
- numero
- localidad
- provincia

### Odontologo

- odontologoId
- matricula
- nombre
- apellido

### Turno

- turnoId
- paciente
- odontologo
- fechaHora

## Requerimientos

- Usar H2 como base de datos
- Testear con JUnit
- Tener View para: Odontologo, Paciente y Turno
- Tener Controller para: Odontologo, Paciente y Turno
- Tener Service para: Odontologo, Paciente y Turno
- Tener DAO para: Odontologo, Domicilio, Paciente y Turno
- Tener Entity para: Odontologo, Domicilio, Paciente y Turno
- Usar @Component, @Repository y @Service según corresponda
- @Autowired para inyección de dependencias
- Usar Hibernate como ORM
- Usar JpaRepositories para las consultas a la base de datos
- Manejar excepciones con Global Exception Handler

## Tests

- Tests integradores con Mock en `test/service`
- Tests unitarios en `test/controller`

## Ejecución del Programa

1. Ejecutar el archivo run de Java.
2. Abrir la dirección `localhost:8080` en un navegador web.
3. Login para admin: Username: `admin` y Contraseña: `admin`.
4. Login para user: Username: `user` y Contraseña: `admin`.

## Instalación

1. Clonar el repositorio:
   sh
   git
   clone [https://github.com/Digital-House-Repos/clinicaspring](https://github.com/Digital-House-Repos/clinicaspring).

2. Navegar al directorio del proyecto:
   sh
   cd proyecto-clinica-dental

3. Construir y ejecutar la aplicación:
   sh
   mvn clean install
   mvn spring-boot:run

## Uso

- Accede a las vistas correspondientes para gestionar Odontólogos, Pacientes y Turnos desde `localhost:8080`.
- Inicia sesión con el usuario `admin` y contraseña `admin` para acceder a todas las funcionalidades.
