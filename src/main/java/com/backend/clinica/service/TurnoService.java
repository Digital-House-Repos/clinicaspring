package com.backend.clinica.service;

import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.repository.db.IDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TurnoService implements IService<TurnoModel> {
    @Autowired
    private IDAO<TurnoModel> interfaceDAO;

    @Override
    public TurnoModel create(TurnoModel turnoModel) {
        return interfaceDAO.create(turnoModel);
    }

    @Override
    public TurnoModel findById(int id) {
        return interfaceDAO.findById(id);
    }

    @Override
    public ArrayList<TurnoModel> findAll() {
        return interfaceDAO.findAll();
    }

    @Override
    public TurnoModel update(TurnoModel turnoModel, Integer id) {
        return interfaceDAO.update(turnoModel, id);
    }

    @Override
    public boolean delete(int id) {
        return interfaceDAO.delete(id);
    }
}
