package com.backend.clinica;

import org.junit.Assert;
import org.junit.Test;
import com.backend.clinica.dao.DBInitializer;
import com.backend.clinica.models.DomicilioModel;
import com.backend.clinica.models.PacienteModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InsertDataTest {
    @Test
    public void insertDataPacienteTest() {
        String fecha = "2021-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);

        DomicilioModel domicilio = new DomicilioModel("Calle Falsa", 123, "Springfield", "Springfield");
        PacienteModel paciente = new PacienteModel("Homero", "Simpson", "12345678", date, domicilio);
        Assert.assertEquals("200", DBInitializer.insertDataPaciente(domicilio, paciente));

        DomicilioModel d2 = new DomicilioModel("Calle Falsa 2", 324, "Austria", "Viena");
        PacienteModel p2 = new PacienteModel("Bart", "Simpson", "87654321", date, d2);
        Assert.assertEquals("200", DBInitializer.insertDataPaciente(d2, p2));

        DomicilioModel d3 = new DomicilioModel("Calle Falsa 3", 345, "Italia", "Roma");
        PacienteModel p3 = new PacienteModel("Lisa", "Simpson", "12348765", date, d3);
        Assert.assertEquals("200", DBInitializer.insertDataPaciente(d3, p3));

        DomicilioModel d4 = new DomicilioModel("Calle Falsa 4", 456, "Francia", "Paris");
        PacienteModel p4 = new PacienteModel("Marge", "Simpson", "87651234", date, d4);
        Assert.assertEquals("200", DBInitializer.insertDataPaciente(d4, p4));
    }
}
