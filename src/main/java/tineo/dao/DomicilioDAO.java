package tineo.dao;

import org.apache.log4j.Logger;
import tineo.models.DomicilioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class DomicilioDAO implements IDAO<DomicilioModel> {
    private static final Logger logger = Logger.getLogger(DomicilioDAO.class);

    @Override
    public DomicilioModel create(DomicilioModel domicilioModel) {
        return null;
    }

    @Override
    public DomicilioModel findById(int id) {
        return null;
    }

    @Override
    public ArrayList<DomicilioModel> findAll() {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ArrayList<DomicilioModel> listDB = new ArrayList<>();
        String query = "SELECT * FROM DOMICILIO;";

        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next()) {
                listDB.add(new DomicilioModel(
                        result.getInt("DOMICILIOID"),
                        result.getString("CALLE"),
                        result.getInt("NUMERO"),
                        result.getString("LOCALIDAD"),
                        result.getString("PROVINCIA")
                ));
            }
            logger.info("GET - Domicilios obtenidos correctamente");
            return listDB;
        } catch (SQLException e) {
            logger.error("GET - Error al obtener los domicilios " + e.getMessage());
            return null;
        }
    }

    @Override
    public DomicilioModel update(DomicilioModel domicilioModel) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
