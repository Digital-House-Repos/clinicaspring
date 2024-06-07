package com.backend.clinica.repository;

import com.backend.clinica.service.OdontologoService;
import com.backend.clinica.service.PacienteService;
import com.backend.clinica.service.TurnoService;
import com.backend.clinica.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DBInitializer {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnoService turnoService;

    public void loadDataToOrm() {
        OdontologoModel o1 = new OdontologoModel("111", "John", "Doe");
        OdontologoModel o2 = new OdontologoModel("222", "Jane", "Smith");
        OdontologoModel o3 = new OdontologoModel("333", "Michael", "Jhonson");

        DomicilioModel d1 = new DomicilioModel("Av. Revolucion", 123, "Mexico", "Mexico");
        DomicilioModel d2 = new DomicilioModel("Calle Corrientes", 456, "Buenos Aires", "Buenos Aires");
        DomicilioModel d3 = new DomicilioModel("Elm Street", 789, "Springwood", "Ohio");

        String f1 = "2021-01-01";
        String f2 = "2022-02-02";
        String f3 = "2023-03-03";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(f1, formatter);
        LocalDate date2 = LocalDate.parse(f2, formatter);
        LocalDate date3 = LocalDate.parse(f3, formatter);

        PacienteModel p1 = new PacienteModel("Emily", "Davis", "901234", date1, d1);
        PacienteModel p2 = new PacienteModel("David", "Wilson", "567890", date2, d2);
        PacienteModel p3 = new PacienteModel("Sarah", "Taylor", "123457", date3, d3);

        odontologoService.create(o1);
        odontologoService.create(o2);
        odontologoService.create(o3);

        pacienteService.create(p1);
        pacienteService.create(p2);
        pacienteService.create(p3);

        String fh1 = "2021-01-01T10:00";
        String fh2 = "2022-02-02T11:00";
        String fh3 = "2023-03-03T12:00";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(fh1, formatter2);
        LocalDateTime dateTime2 = LocalDateTime.parse(fh2, formatter2);
        LocalDateTime dateTime3 = LocalDateTime.parse(fh3, formatter2);

        TurnoModel t1 = new TurnoModel(p1, o1, dateTime1);
        TurnoModel t2 = new TurnoModel(p2, o2, dateTime2);
        TurnoModel t3 = new TurnoModel(p3, o3, dateTime3);

        turnoService.create(t1);
        turnoService.create(t2);
        turnoService.create(t3);
    }
}
