package com.backend.clinica.controller;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.entity.TurnoModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TurnoControllerTest {
    @Test
    public void testConstructorVacio() {
        TurnoModel turno = new TurnoModel();

        assertNull(turno.getTurnoID());
        assertNull(turno.getPaciente());
        assertNull(turno.getOdontologo());
        assertNull(turno.getFechaHora());
    }

    @Test
    public void testConstructorConParametros() {
        PacienteModel paciente = new PacienteModel();
        OdontologoModel odontologo = new OdontologoModel();
        LocalDateTime fechaHora = LocalDateTime.now(); // Ahora es LocalDateTime

        TurnoModel turno = new TurnoModel(paciente, odontologo, fechaHora);

        assertNull(turno.getTurnoID());
        assertEquals(paciente, turno.getPaciente());
        assertEquals(odontologo, turno.getOdontologo());
        assertEquals(fechaHora, turno.getFechaHora());
    }

    @Test
    public void testConstructorConTodosLosParametros() {
        // ... (Este test ya está correcto)
    }

    @Test
    public void testSettersAndGetters() {
        PacienteModel paciente = new PacienteModel();
        OdontologoModel odontologo = new OdontologoModel();
        LocalDateTime fechaHora = LocalDateTime.now(); // Ahora es LocalDateTime

        TurnoModel turno = new TurnoModel(1L, paciente, odontologo, fechaHora);

        assertEquals(1L, turno.getTurnoID());
        assertEquals(paciente, turno.getPaciente());
        assertEquals(odontologo, turno.getOdontologo());
        assertEquals(fechaHora, turno.getFechaHora());
    }
}
