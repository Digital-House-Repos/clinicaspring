package com.backend.clinica.service;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.repository.IDAO;
import com.backend.clinica.repository.TurnoDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TurnoService implements IService<TurnoModel> {
    private final IDAO<TurnoModel> interfaceDAO;

    public TurnoService() {
        this.interfaceDAO = new TurnoDAO();
        String dateTimeStr = "2024-01-01T10:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateLD = LocalDateTime.parse(dateTimeStr, formatter);

        PacienteModel p1 = new PacienteService().findById(1);
        OdontologoModel o1 = new OdontologoService().findById(1);
        TurnoModel t1 = new TurnoModel(p1, o1, dateLD);

        this.interfaceDAO.create(t1);
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
