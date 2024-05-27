package tineo.service;

import tineo.dao.DBInitializer;
import tineo.dao.IDAO;
import tineo.dao.PacienteDAO;
import tineo.models.PacienteModel;

import java.util.ArrayList;

public class PacienteService implements IService<PacienteModel> {

    private final IDAO<PacienteModel> interfaceDAO;

    public PacienteService() {
        this.interfaceDAO = new PacienteDAO();
        DBInitializer.deleteTableDomicilio();
        DBInitializer.createTableDomicilio();
        DBInitializer.deleteTablePaciente();
        DBInitializer.createTablePaciente();
    }

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
