package com.backend.clinica.repository;

import com.backend.clinica.entity.DomicilioModel;
import com.backend.clinica.entity.PacienteModel;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;

@Repository
public class PacienteDAO implements IDAO<PacienteModel> {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PacienteDAO.class);

    @Override
    public PacienteModel create(PacienteModel pacienteModel) {
        DBConnector connector = DBConnector.getInstance();
        String queryPaciente = "INSERT INTO PACIENTE (NOMBRE, APELLIDO, DNI, FECHAINGRESO, DOMICILIOID) VALUES (?, ?, ?, ?, ?)";

        try {
            DomicilioDAO domicilioDAO = new DomicilioDAO();
            DomicilioModel domicilioModel = domicilioDAO.create(pacienteModel.getDomicilio());
            Connection connection = connector.getConnection();

            PreparedStatement psPaciente = connection.prepareStatement(queryPaciente, PreparedStatement.RETURN_GENERATED_KEYS);
            psPaciente.setString(1, pacienteModel.getNombre());
            psPaciente.setString(2, pacienteModel.getApellido());
            psPaciente.setString(3, pacienteModel.getDni());
            psPaciente.setDate(4, java.sql.Date.valueOf(pacienteModel.getFechaIngreso()));
            psPaciente.setInt(5, domicilioModel.getDomicilioID());

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
        } finally {
            connector.closeConnection();
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
        } finally {
            connector.closeConnection();
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
                DomicilioModel domicilioPaciente = new DomicilioDAO().findById(result.getInt("DOMICILIOID"));
                listDB.add(new PacienteModel(
                        result.getInt("PACIENTEID"),
                        result.getString("NOMBRE"),
                        result.getString("APELLIDO"),
                        result.getString("DNI"),
                        result.getDate("FECHAINGRESO").toLocalDate(),
                        domicilioPaciente
                ));
            }

            logger.info("GET - Pacientes obtenidos correctamente");
            return listDB;
        } catch (SQLException e) {
            logger.error("GET - Error al obtener los pacientes " + e.getMessage());
            return null;
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public PacienteModel update(PacienteModel pacienteModel, Integer id) {
        PacienteModel pacienteDB = findById(id);
        if (pacienteDB == null) {
            logger.error("El paciente con id " + id + " no existe");
            return null;
        }
        Integer domicilioID = pacienteDB.getDomicilio().getDomicilioID();

        DomicilioDAO domicilioDAO = new DomicilioDAO();
        DomicilioModel domicilioDB = domicilioDAO.update(pacienteModel.getDomicilio(), domicilioID);
        if (domicilioDB == null) {
            logger.error("No se ha podido actualizar el domicilio del paciente");
            return null;
        }

        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "UPDATE PACIENTE SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHAINGRESO = ?, DOMICILIOID = ? WHERE PACIENTEID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pacienteModel.getNombre());
            statement.setString(2, pacienteModel.getApellido());
            statement.setString(3, pacienteModel.getDni());
            statement.setDate(4, java.sql.Date.valueOf(pacienteModel.getFechaIngreso()));
            statement.setInt(5, domicilioID);
            statement.setInt(6, id);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                logger.info("Paciente actualizado correctamente");
                pacienteModel.setPacienteID(id);
                pacienteModel.setDomicilio(domicilioDB);
                return pacienteModel;
            }
            logger.error("No se ha podido actualizar el paciente");
            return null;
        } catch (SQLException e) {
            logger.error("PUT - Error al actualizar el paciente " + e.getMessage());
            return null;
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public boolean delete(int id) {
        PacienteModel pacienteDB = findById(id);
        if (pacienteDB == null) {
            logger.error("El paciente con id " + id + " no existe");
            return false;
        }
        Integer domicilioID = pacienteDB.getDomicilio().getDomicilioID();

        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();

        String query = "DELETE FROM PACIENTE WHERE PACIENTEID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                logger.info("Paciente eliminado correctamente");
                DomicilioDAO domicilioDAO = new DomicilioDAO();
                domicilioDAO.delete(domicilioID);
                return true;
            }
            logger.error("No se ha podido eliminar el paciente");
            return false;
        } catch (SQLException e) {
            logger.error("DELETE - Error al eliminar el paciente " + e.getMessage());
            return false;
        }
    }
}
