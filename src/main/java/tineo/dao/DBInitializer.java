package tineo.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;

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
}
