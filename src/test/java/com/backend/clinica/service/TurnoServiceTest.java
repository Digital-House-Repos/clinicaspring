package com.backend.clinica.service;

import com.backend.clinica.entity.DomicilioModel;
import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.exception.BadRequestException;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnoServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    public TurnoModel cargarDatos(String dni, String matricula) throws EntityAlreadyExistsException, BadRequestException {
        PacienteModel pacienteGuardado = pacienteService.create(new PacienteModel("Claudia", "Heredia", dni, LocalDate.of(2024, 6, 19), new DomicilioModel("La Esperanza", 345, "San Juan", "Hibaner")));
        OdontologoModel odontolgoGuardado = odontologoService.create(new OdontologoModel(matricula, "FLAVIO", "GONZALES"));
        TurnoModel turnoGuardado = turnoService.create(new TurnoModel(pacienteGuardado, odontolgoGuardado, LocalDateTime.now()));
        turnoService.create(turnoGuardado);
        return turnoGuardado;
    }

    @Test
    void testGetTurnos() throws Exception {
        cargarDatos("999888777", "AF23");
        MvcResult respuesta = mockMvc.perform(get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());

    }

    @Test
    void testGetTurnoById() throws Exception {
        TurnoModel turnoEsperado = cargarDatos("666555444", "AF24");

        assertNotNull(turnoEsperado, "El turno esperado no debe ser null");

        MvcResult respuesta = mockMvc.perform(get("/turnos/{id}", turnoEsperado.getTurnoID()).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty(), "La respuesta no debe estar vacía");
    }
}