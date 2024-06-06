package com.backend.clinica;

import com.backend.clinica.entity.DomicilioModel;
import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.entity.PacienteModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ClinicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicaApplication.class, args);
//        OdontologoModel o1 = new OdontologoModel("12345678", "Homero", "Simpson");
//        OdontologoModel o2 = new OdontologoModel("87654321", "Bart", "Simpson");
//        OdontologoModel o3 = new OdontologoModel("12348765", "Lisa", "Simpson");
//
//        DBInitializer.deleteTableOdontologo();
//        DBInitializer.createTableOdontologo();
//        DBInitializer.insertDataOdontologo(o1);
//        DBInitializer.insertDataOdontologo(o2);
//        DBInitializer.insertDataOdontologo(o3);
//
//
//        String fecha = "2021-01-01";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate date = LocalDate.parse(fecha, formatter);
//
//        DomicilioModel d1 = new DomicilioModel("Calle Falsa", 123, "Lisboa", "Portugal");
//        PacienteModel p1 = new PacienteModel("Homero", "Simpson", "12345678", date, d1);
//
//        DomicilioModel d2 = new DomicilioModel("Calle Falsa 2", 324, "Austria", "Viena");
//        PacienteModel p2 = new PacienteModel("Bart", "Simpson", "87654321", date, d2);
//
//        DomicilioModel d3 = new DomicilioModel("Calle Falsa 3", 345, "Italia", "Roma");
//        PacienteModel p3 = new PacienteModel("Lisa", "Simpson", "12348765", date, d3);
//
//        DBInitializer.deleteTablePaciente();
//        DBInitializer.deleteTableDomicilio();
//        DBInitializer.createTableDomicilio();
//        DBInitializer.createTablePaciente();
//        DBInitializer.insertDataPaciente(d1, p1);
//        DBInitializer.insertDataPaciente(d2, p2);
//        DBInitializer.insertDataPaciente(d3, p3);
    }
}
