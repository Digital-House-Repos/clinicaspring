package com.backend.clinica.service;

import com.backend.clinica.repository.IDAO;
import com.backend.clinica.entity.PacienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PacienteService implements IService<PacienteModel> {
    @Autowired
    private IDAO<PacienteModel> interfaceDAO;

    @Override
    public PacienteModel create(PacienteModel pacienteModel) {
        return interfaceDAO.create(pacienteModel);
    }

    @Override
    public PacienteModel findById(int id) {
        return interfaceDAO.findById(id);
    }

    @Override
    public ArrayList<PacienteModel> findAll() {
        return interfaceDAO.findAll();
    }

    @Override
    public PacienteModel update(PacienteModel pacienteModel, Integer id) {
        return interfaceDAO.update(pacienteModel, id);
    }

    @Override
    public boolean delete(int id) {
        return interfaceDAO.delete(id);
    }
}
