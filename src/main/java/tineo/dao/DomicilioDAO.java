package tineo.dao;

import org.apache.log4j.Logger;
import tineo.models.DomicilioModel;

import java.sql.*;

import java.util.ArrayList;

public class DomicilioDAO implements IDAO<DomicilioModel> {
    private static final Logger logger = Logger.getLogger(DomicilioDAO.class);

    @Override
    public DomicilioModel create(DomicilioModel domicilioModel) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "INSERT INTO DOMICILIO (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, domicilioModel.getCalle());
            preparedStatement.setInt(2, domicilioModel.getNumero());
            preparedStatement.setString(3, domicilioModel.getLocalidad());
            preparedStatement.setString(4, domicilioModel.getProvincia());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet result = preparedStatement.getGeneratedKeys();
                result.next();
                logger.info("POST - Domicilio creado correctamente con ID " + result.getInt(1));
                return domicilioModel;
            } else {
                logger.error("POST - Error al crear el domicilio");
                return null;
            }
        } catch (SQLException e) {
            logger.error("POST - Error al crear el domicilio: " + e.getMessage());
            return null;
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public DomicilioModel findById(int id) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "SELECT * FROM DOMICILIO WHERE DOMICILIOID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                logger.info("GET - Domicilio con ID " + id + " obtenido correctamente");
                return new DomicilioModel(
                        result.getInt("DOMICILIOID"),
                        result.getString("CALLE"),
                        result.getInt("NUMERO"),
                        result.getString("LOCALIDAD"),
                        result.getString("PROVINCIA")
                );
            }
            logger.error("GET - Domicilio con ID " + id + " no encontrado");
            return null;
        } catch (SQLException e) {
            logger.error("GET - Error al obtener el domicilio con ID " + id + ": " + e.getMessage());
            return null;
        }
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
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public DomicilioModel update(DomicilioModel domicilioModel, Integer id) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        DomicilioModel result = this.findById(id);

        if (result == null) {
            logger.error("PUT - Domicilio con ID " + id + " no encontrado");
            return null;
        }

        String query = "UPDATE DOMICILIO SET CALLE = ?, NUMERO = ?, LOCALIDAD = ?, PROVINCIA = ? WHERE DOMICILIOID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, domicilioModel.getCalle());
            preparedStatement.setInt(2, domicilioModel.getNumero());
            preparedStatement.setString(3, domicilioModel.getLocalidad());
            preparedStatement.setString(4, domicilioModel.getProvincia());
            preparedStatement.setInt(5, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("PUT - Domicilio con ID " + id + " actualizado correctamente");
                return domicilioModel;
            } else {
                logger.error("PUT - Error al actualizar el domicilio con ID " + id);
                return null;
            }
        } catch (SQLException e) {
            logger.error("PUT - Error al actualizar el domicilio con ID " + id + ": " + e.getMessage());
            return null;
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public boolean delete(int id) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        DomicilioModel result = this.findById(id);

        if (result == null) {
            logger.error("DELETE - Domicilio con ID " + id + " no encontrado");
            return false;
        }

        String query = "DELETE FROM DOMICILIO WHERE DOMICILIOID = ? LIMIT 1;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("DELETE - Domicilio con ID " + id + " eliminado correctamente");
                return true;
            } else {
                logger.error("DELETE - Error al eliminar el domicilio con ID " + id);
                return false;
            }
        } catch (SQLException e) {
            logger.error("DELETE - Error al eliminar el domicilio con ID " + id + ": " + e.getMessage());
            return false;
        } finally {
            connector.closeConnection();
        }
    }

    public void createId(DomicilioModel domicilioModel) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        String query = "INSERT INTO DOMICILIO (DOMICILIOID, CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, domicilioModel.getDomicilioID());
            preparedStatement.setString(2, domicilioModel.getCalle());
            preparedStatement.setInt(3, domicilioModel.getNumero());
            preparedStatement.setString(4, domicilioModel.getLocalidad());
            preparedStatement.setString(5, domicilioModel.getProvincia());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet result = preparedStatement.getGeneratedKeys();
                result.next();
                logger.info("POST - Domicilio creado correctamente con ID " + result.getInt(1));
            } else {
                logger.error("POST - Error al crear el domicilio");
            }
        } catch (SQLException e) {
            logger.error("POST - Error al crear el domicilio: " + e.getMessage());
        } finally {
            connector.closeConnection();
        }
    }
}
