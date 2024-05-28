package com.backend.clinica.dao;

import org.apache.log4j.Logger;
import com.backend.clinica.models.OdontologoModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;

public class OdontologoDAO implements IDAO<OdontologoModel> {
    private static final Logger logger = Logger.getLogger(OdontologoDAO.class);

    @Override
    public OdontologoModel create(OdontologoModel odontologoModel) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "INSERT INTO ODONTOLOGO (NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, odontologoModel.getNumeroMatricula());
            preparedStatement.setString(2, odontologoModel.getNombre());
            preparedStatement.setString(3, odontologoModel.getApellido());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet result = preparedStatement.getGeneratedKeys();
                result.next();
                logger.info("POST - ODONTOLOGO creado correctamente con ID " + result.getInt(1));
                odontologoModel.setOdontologoID(result.getInt(1));
                return odontologoModel;
            } else {
                logger.error("POST - Error al crear el ODONTOLOGO");
                return null;
            }
        } catch (SQLException e) {
            logger.error("POST - Error al crear el ODONTOLOGO: " + e.getMessage());
            return null;
        }
    }

    @Override
    public OdontologoModel findById(int id) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "SELECT * FROM ODONTOLOGO WHERE ODONTOLOGOID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                logger.info("GET - ODONTOLOGO con ID " + id + " obtenido correctamente");
                return new OdontologoModel(
                        result.getInt("ODONTOLOGOID"),
                        result.getString("NUMEROMATRICULA"),
                        result.getString("NOMBRE"),
                        result.getString("APELLIDO"));
            }
            logger.error("GET - ODONTOLOGO con ID " + id + " no encontrado");
            return null;
        } catch (SQLException e) {
            logger.error("GET - Error al obtener el ODONTOLOGO con ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<OdontologoModel> findAll() {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ArrayList<OdontologoModel> listDB = new ArrayList<>();
        String query = "SELECT * FROM ODONTOLOGO";

        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next()) {
                listDB.add(new OdontologoModel(
                        result.getInt("ODONTOLOGOID"),
                        result.getString("NUMEROMATRICULA"),
                        result.getString("NOMBRE"),
                        result.getString("APELLIDO")));
            }
            logger.info("GET - ODONTOLOGO obtenidos correctamente");
            return listDB;
        } catch (SQLException e) {
            logger.error("GET - Error al obtener los ODONTOLOGO " + e.getMessage());
            return null;
        }
    }

    @Override
    public OdontologoModel update(OdontologoModel odontologoModel, Integer id) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "UPDATE ODONTOLOGO SET NUMEROMATRICULA = ?, NOMBRE = ?, APELLIDO = ? WHERE ODONTOLOGOID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, odontologoModel.getNumeroMatricula());
            preparedStatement.setString(2, odontologoModel.getNombre());
            preparedStatement.setString(3, odontologoModel.getApellido());
            preparedStatement.setInt(4, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("PUT - ODONTOLOGO con ID " + id + " actualizado correctamente");
                odontologoModel.setOdontologoID(id);
                return odontologoModel;
            } else {
                logger.error("PUT - Error al actualizar el ODONTOLOGO con ID " + id);
                return null;
            }
        } catch (SQLException e) {
            logger.error("PUT - Error al actualizar el ODONTOLOGO con ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "DELETE FROM ODONTOLOGO WHERE ODONTOLOGOID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("DELETE - ODONTOLOGO con ID " + id + " eliminado correctamente");
                return true;
            } else {
                logger.error("DELETE - Error al eliminar el ODONTOLOGO con ID " + id);
                return false;
            }
        } catch (SQLException e) {
            logger.error("DELETE - Error al eliminar el ODONTOLOGO con ID " + id + ": " + e.getMessage());
            return false;
        }
    }
}
