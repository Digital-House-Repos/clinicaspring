package com.backend.clinica.repository.db;

import org.apache.log4j.Logger;
import com.backend.clinica.entity.DomicilioModel;
import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.entity.PacienteModel;

import java.sql.*;

public class DBInitializer {
    private static final Logger logger = Logger.getLogger(DBInitializer.class);

    public static String deleteTableDomicilio() {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "DROP TABLE IF EXISTS DOMICILIO";

        try {
            int result = connection.createStatement().executeUpdate(query);
            if (result == 0) {
                logger.info("Tabla DOMICILIO borrada");
                return "200";
            } else {
                logger.error("No existe la tabla DOMICILIO");
                return "404";
            }
        } catch (Exception e) {
            logger.error("Error al borrar la tabla DOMICILIO - " + e.getMessage());
            return "500";
        } finally {
            connector.closeConnection();
        }
    }

    public static String createTableDomicilio() {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "CREATE TABLE IF NOT EXISTS DOMICILIO (" +
                "    DOMICILIOID INT PRIMARY KEY AUTO_INCREMENT," +
                "    CALLE VARCHAR(255)," +
                "    NUMERO INT," +
                "    LOCALIDAD VARCHAR(255)," +
                "    PROVINCIA VARCHAR(255)" +
                ");";

        try {
            int result = connection.createStatement().executeUpdate(query);
            if (result == 0) {
                logger.info("Tabla DOMICILIO creada");
                return "200";
            } else {
                logger.error("Error al crear la tabla DOMICILIO");
                return "500";
            }
        } catch (Exception e) {
            logger.error("Error al crear la tabla DOMICILIO - " + e.getMessage());
            return "500";
        } finally {
            connector.closeConnection();
        }
    }

    public static String deleteTableOdontologo() {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "DROP TABLE IF EXISTS ODONTOLOGO;";

        try {
            int result = connection.createStatement().executeUpdate(query);
            if (result == 0) {
                logger.info("Tabla ODONTOLOGO borrada");
                return "200";
            } else {
                logger.error("No existe la tabla ODONTOLOGO");
                return "404";
            }
        } catch (Exception e) {
            logger.error("Error al borrar la tabla ODONTOLOGO - " + e.getMessage());
            return "500";
        } finally {
            connector.closeConnection();
        }
    }

    public static String createTableOdontologo() {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "CREATE TABLE IF NOT EXISTS ODONTOLOGO (" +
                "    ODONTOLOGOID INT PRIMARY KEY AUTO_INCREMENT," +
                "    NUMEROMATRICULA VARCHAR(255) UNIQUE NOT NULL," +
                "    NOMBRE VARCHAR(255) NOT NULL," +
                "    APELLIDO VARCHAR(255) NOT NULL" +
                ");";

        try {
            int result = connection.createStatement().executeUpdate(query);
            if (result == 0) {
                logger.info("Tabla ODONTOLOGO creada");
                return "200";
            } else {
                logger.error("Error al crear la tabla ODONTOLOGO");
                return "500";
            }
        } catch (Exception e) {
            logger.error("Error al crear la tabla ODONTOLOGO - " + e.getMessage());
            return "500";
        } finally {
            connector.closeConnection();
        }
    }

    public static String deleteTablePaciente() {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "DROP TABLE IF EXISTS PACIENTE";

        try {
            int result = connection.createStatement().executeUpdate(query);
            if (result == 0) {
                logger.info("Tabla PACIENTE borrada");
                return "200";
            } else {
                logger.error("No existe la tabla PACIENTE");
                return "404";
            }
        } catch (Exception e) {
            logger.error("Error al borrar la tabla PACIENTE - " + e.getMessage());
            return "500";
        } finally {
            connector.closeConnection();
        }
    }

    public static String createTablePaciente() {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "CREATE TABLE IF NOT EXISTS PACIENTE (" +
                "    PACIENTEID INT PRIMARY KEY AUTO_INCREMENT," +
                "    NOMBRE VARCHAR(255)," +
                "    APELLIDO VARCHAR(255)," +
                "    DNI VARCHAR(255) UNIQUE," +
                "    FECHAINGRESO DATE," +
                "    DOMICILIOID INT NOT NULL," +
                "    FOREIGN KEY (DOMICILIOID) REFERENCES DOMICILIO(DOMICILIOID) ON UPDATE CASCADE ON DELETE CASCADE" +
                ");";

        try {

            int result = connection.createStatement().executeUpdate(query);
            if (result == 0) {
                logger.info("Tabla PACIENTE creada");
                return "200";
            } else {
                logger.error("Error al crear la tabla PACIENTE");
                return "500";
            }
        } catch (Exception e) {
            logger.error("Error al crear la tabla PACIENTE - " + e.getMessage());
            return "500";
        } finally {
            connector.closeConnection();
        }
    }

    public static String insertDataPaciente(DomicilioModel domicilio, PacienteModel paciente) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String queryDomilicio = "INSERT INTO DOMICILIO (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?, ?, ?, ?)";
        String queryPaciente = "INSERT INTO PACIENTE (NOMBRE, APELLIDO, DNI, FECHAINGRESO, DOMICILIOID) VALUES (?, ?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);
            connection.commit();

            PreparedStatement psDomicilio = connection.prepareStatement(queryDomilicio, Statement.RETURN_GENERATED_KEYS);
            psDomicilio.setString(1, domicilio.getCalle());
            psDomicilio.setInt(2, domicilio.getNumero());
            psDomicilio.setString(3, domicilio.getLocalidad());
            psDomicilio.setString(4, domicilio.getProvincia());

            int rowsDomicilio = psDomicilio.executeUpdate();
            if (rowsDomicilio > 0) {
                ResultSet rs = psDomicilio.getGeneratedKeys();
                rs.next();
                domicilio.setDomicilioID(rs.getInt(1));
                logger.info(rowsDomicilio + " Dato insertados en la tabla DOMICILIO");
            } else {
                logger.error("No se ha creado el registro en la tabla DOMICILIO");
                connection.rollback();
                return "500";
            }

            PreparedStatement psPaciente = connection.prepareStatement(queryPaciente);
            psPaciente.setString(1, paciente.getNombre());
            psPaciente.setString(2, paciente.getApellido());
            psPaciente.setString(3, paciente.getDni());
            psPaciente.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psPaciente.setInt(5, domicilio.getDomicilioID());

            int rowsPaciente = psPaciente.executeUpdate();
            if (rowsPaciente > 0) {
                logger.info(rowsPaciente + " Dato insertados en la tabla PACIENTE");
                connection.setAutoCommit(true);
                return "200";
            } else {
                logger.error("No se ha creado el registro en la tabla PACIENTE");
                connection.rollback();
                return "500";
            }
        } catch (SQLException e) {
            logger.error("Error al insertar datos en la tabla PACIENTE - " + e.getMessage());
            return "500";
        } finally {
            connector.closeConnection();
        }
    }

    public static String insertDataOdontologo(OdontologoModel odontologo) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "INSERT INTO ODONTOLOGO (NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet result = preparedStatement.getGeneratedKeys();
                result.next();
                logger.info("POST - ODONTOLOGO creado correctamente con ID " + result.getInt(1));
                return "200";
            } else {
                logger.error("POST - Error al crear el ODONTOLOGO");
                return "500";
            }
        } catch (SQLException e) {
            logger.error("POST - Error al crear el ODONTOLOGO: " + e.getMessage());
            return "500";
        } finally {
            connector.closeConnection();
        }
    }
}
