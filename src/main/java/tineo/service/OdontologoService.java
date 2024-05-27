package tineo.service;

import tineo.dao.DBInitializer;
import tineo.dao.IDAO;
import tineo.dao.OdontologoDAO;
import tineo.models.OdontologoModel;

import java.util.ArrayList;

public class OdontologoService implements IService<OdontologoModel> {

    private final IDAO<OdontologoModel> interfaceDAO;

    public OdontologoService() {
        this.interfaceDAO = new OdontologoDAO();
        DBInitializer.deleteTableOdontologo();
        DBInitializer.createTableOdontologo();
    }

    @Override
    public OdontologoModel create(OdontologoModel odontologoModel) {
        return interfaceDAO.create(odontologoModel);
    }

    @Override
    public OdontologoModel findById(int id) {
        return interfaceDAO.findById(id);
    }

    @Override
    public ArrayList<OdontologoModel> findAll() {
        return interfaceDAO.findAll();
    }

    @Override
    public OdontologoModel update(OdontologoModel odontologoModel, Integer id) {
        return interfaceDAO.update(odontologoModel, id);
    }

    @Override
    public boolean delete(int id) {
        return interfaceDAO.delete(id);
    }
}
