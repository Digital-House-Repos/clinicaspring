package com.backend.clinica.repository;

import org.junit.Assert;
import org.junit.Test;
import com.backend.clinica.entity.DomicilioModel;
import com.backend.clinica.entity.PacienteModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PacienteDAOTest {

    @Test
    public void findAllTest() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        ArrayList<PacienteModel> listaPaciente = pacienteDAO.findAll();
        Assert.assertNotNull(pacienteDAO.findAll());
    }

    @Test
    public void findByIdTest() {
        String queryResult = "Paciente [pacienteID=1, nombre='Homero', apellido='Simpson', dni='12345678', fechaIngreso='2021-01-01', domicilioID=Domicilio [domicilioID=1, calle='Calle Falsa', numero=123, localidad='Springfield', provincia='Springfield']]";
        PacienteDAO pacienteDAO = new PacienteDAO();
        PacienteModel paciente = pacienteDAO.findById(1);
        Assert.assertEquals(queryResult, paciente.toString());
    }

    @Test
    public void createTest() {
        String fecha = "2024-04-04";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);

        DomicilioModel domicilio = new DomicilioModel(1, "DOMICILIO", 1111, "DEMO", "DEMO");
        PacienteModel paciente = new PacienteModel(1, "PACIENTE", "DEMO", "DEMO", date, domicilio);
        PacienteDAO pacienteDAO = new PacienteDAO();
        PacienteModel pacienteCreado = pacienteDAO.create(paciente);
        Assert.assertNotNull(pacienteCreado);
    }
}
