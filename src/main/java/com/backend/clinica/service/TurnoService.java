package com.backend.clinica.service;

import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.repository.IDAO;
import com.backend.clinica.repository.TurnoDAO;

import java.util.ArrayList;

public class TurnoService implements IService<TurnoModel> {
    private final IDAO<TurnoModel> interfaceDAO;

    public TurnoService() {
        this.interfaceDAO = new TurnoDAO();
    }

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
