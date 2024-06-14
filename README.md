# Proyecto Clínica Dental

Una clínica dental necesita informatizar su operatoria. Por lo cual, se solicita un sistema para la gestión de sus
pacientes, donde puedan darlos de alta, buscarlos y listarlos.

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

1. Agregar un paciente con domicilio y buscarlo en la base de datos por ID.
2. Eliminar el paciente anteriormente creado y luego buscarlo, debe tener también el domicilio.
3. Buscar e imprimir en consola todos los pacientes con sus domicilios, serán los pacientes que agregamos antes de
   ejecutar el primer test.

## Ejecución del Programa

1. Ejecutar el archivo run de Java.
2. Abrir la dirección `localhost:8080` en un navegador web.

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

Accede a las vistas correspondientes para gestionar Odontólogos, Pacientes y Turnos desde `localhost:8080`.
