package tineo.dao;

import tineo.models.DomicilioModel;
import tineo.models.PacienteModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteDAO implements IDAO<PacienteModel> {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PacienteDAO.class);

    @Override
    public PacienteModel create(PacienteModel pacienteModel) {
        return null;
    }

    @Override
    public PacienteModel findById(int id) {
        return null;
    }

    @Override
    public ArrayList<PacienteModel> findAll() {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ArrayList<PacienteModel> listDB = new ArrayList<>();
        String query = "SELECT * FROM PACIENTE;";

        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next()) {
                DomicilioDAO domicilioDAO = new DomicilioDAO();
                DomicilioModel domicilioModel = domicilioDAO.findById(result.getInt("DOMICILIOID"));
                listDB.add(new PacienteModel(
                        result.getInt("PACIENTEID"),
                        result.getString("NOMBRE"),
                        result.getString("APELLIDO"),
                        result.getString("DNI"),
                        result.getDate("FECHAINGRESO").toLocalDate(),
                        domicilioModel
                ));
            }
            logger.info("GET - Pacientes obtenidos correctamente");
            return listDB;
        } catch (SQLException e) {
            logger.error("GET - Error al obtener los pacientes " + e.getMessage());
            return null;
        }
    }

    @Override
    public PacienteModel update(PacienteModel pacienteModel, Integer id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
