package com.backend.clinica.service;

import com.backend.clinica.repository.IDAO;
import com.backend.clinica.entity.OdontologoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OdontologoService implements IService<OdontologoModel> {

    @Autowired
    private IDAO<OdontologoModel> interfaceDAO;

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
