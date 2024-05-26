package tineo.dao;

import tineo.models.DomicilioModel;
import tineo.models.PacienteModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;

public class PacienteDAO implements IDAO<PacienteModel> {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PacienteDAO.class);

    @Override
    public PacienteModel create(PacienteModel pacienteModel) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String queryPaciente = "INSERT INTO PACIENTE (NOMBRE, APELLIDO, DNI, FECHAINGRESO, DOMICILIOID) VALUES (?, ?, ?, ?, ?)";

        try {
            DomicilioDAO domicilioDAO = new DomicilioDAO();
            DomicilioModel domicilioModel = domicilioDAO.create(pacienteModel.getDomicilioID());

            PreparedStatement psPaciente = connection.prepareStatement(queryPaciente, PreparedStatement.RETURN_GENERATED_KEYS);
            psPaciente.setString(1, pacienteModel.getNombre());
            psPaciente.setString(2, pacienteModel.getApellido());
            psPaciente.setString(3, pacienteModel.getDni());
            psPaciente.setDate(4, java.sql.Date.valueOf(pacienteModel.getFechaIngreso()));
            psPaciente.setInt(5, pacienteModel.getDomicilioID().getDomicilioID());

            int rowsPaciente = psPaciente.executeUpdate();
            if (rowsPaciente > 0) {
                ResultSet rs = psPaciente.getGeneratedKeys();
                rs.next();
                pacienteModel.setPacienteID(rs.getInt(1));
                logger.info(rowsPaciente + " Dato insertados en la tabla PACIENTE");
                return pacienteModel;
            } else {
                logger.error("No se ha creado el registro en la tabla PACIENTE");
                return null;
            }
        } catch (SQLException e) {
            logger.error("POST - ERROR " + e.getMessage());
            return null;
        }
    }

    @Override
    public PacienteModel findById(int id) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "SELECT * FROM PACIENTE WHERE PACIENTEID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                DomicilioModel domicilioPaciente = new DomicilioDAO().findById(result.getInt("DOMICILIOID"));
                return new PacienteModel(
                        result.getInt("PACIENTEID"),
                        result.getString("NOMBRE"),
                        result.getString("APELLIDO"),
                        result.getString("DNI"),
                        result.getDate("FECHAINGRESO").toLocalDate(),
                        domicilioPaciente);
            }
            return null;
        } catch (SQLException e) {
            System.out.println("GET error: " + e.getMessage());
            return null;
        }
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
