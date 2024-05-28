package com.backend.clinica.service;

import com.backend.clinica.dao.DBInitializer;
import com.backend.clinica.dao.IDAO;
import com.backend.clinica.dao.OdontologoDAO;
import com.backend.clinica.models.OdontologoModel;

import java.util.ArrayList;

public class OdontologoService implements IService<OdontologoModel> {

    private final IDAO<OdontologoModel> interfaceDAO;

    public OdontologoService() {
        OdontologoModel o1 = new OdontologoModel("12345678", "Homero", "Simpson");
        OdontologoModel o2 = new OdontologoModel("87654321", "Bart", "Simpson");
        OdontologoModel o3 = new OdontologoModel("12348765", "Lisa", "Simpson");

        this.interfaceDAO = new OdontologoDAO();
        DBInitializer.deleteTableOdontologo();
        DBInitializer.createTableOdontologo();
        DBInitializer.insertDataOdontologo(o1);
        DBInitializer.insertDataOdontologo(o2);
        DBInitializer.insertDataOdontologo(o3);
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
