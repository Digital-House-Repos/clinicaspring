package clinica.service;

import clinica.dao.DBInitializer;
import clinica.dao.IDAO;
import clinica.dao.PacienteDAO;
import clinica.models.DomicilioModel;
import clinica.models.PacienteModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PacienteService implements IService<PacienteModel> {

    private final IDAO<PacienteModel> interfaceDAO;

    public PacienteService() {
        String fecha = "2021-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);

        DomicilioModel d1 = new DomicilioModel("Calle Falsa", 123, "Springfield", "Springfield");
        PacienteModel p1 = new PacienteModel("Homero", "Simpson", "12345678", date, d1);

        DomicilioModel d2 = new DomicilioModel("Calle Falsa 2", 324, "Austria", "Viena");
        PacienteModel p2 = new PacienteModel("Bart", "Simpson", "87654321", date, d2);

        DomicilioModel d3 = new DomicilioModel("Calle Falsa 3", 345, "Italia", "Roma");
        PacienteModel p3 = new PacienteModel("Lisa", "Simpson", "12348765", date, d3);

        DomicilioModel d4 = new DomicilioModel("Calle Falsa 4", 456, "Francia", "Paris");
        PacienteModel p4 = new PacienteModel("Marge", "Simpson", "87651234", date, d4);

        this.interfaceDAO = new PacienteDAO();
        DBInitializer.deleteTableDomicilio();
        DBInitializer.createTableDomicilio();
        DBInitializer.deleteTablePaciente();
        DBInitializer.createTablePaciente();
        DBInitializer.insertDataPaciente(d1, p1);
        DBInitializer.insertDataPaciente(d2, p2);
        DBInitializer.insertDataPaciente(d3, p3);
        DBInitializer.insertDataPaciente(d4, p4);
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
