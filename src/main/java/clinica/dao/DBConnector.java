package clinica.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private final static Logger logger = Logger.getLogger(DBConnector.class);
    private static DBConnector instance = null;
    private Connection connection = null;

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    private void connectToDB() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        String databaseURL = "jdbc:h2:tcp://localhost/~/test";
        String databaseUser = "sa";
        String databasePassword = "";

        connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);

        String databaseName = connection.getMetaData().getDatabaseProductName();
        String databaseVersion = connection.getMetaData().getDatabaseProductVersion();
        logger.info("Connected to " + databaseName + " " + databaseVersion);
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                this.connectToDB();
            }
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error DB: " + e.getMessage());
            return null;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                logger.info("Connection closed");
            }
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
        }
    }
}
