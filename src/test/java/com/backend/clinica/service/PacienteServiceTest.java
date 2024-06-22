package com.backend.clinica.service;

import com.backend.clinica.entity.DomicilioModel;
import com.backend.clinica.entity.PacienteModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacienteServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PacienteService pacienteService;

    @Test
    public void getPacientes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Pacientes encontrados"));
    }

    @Test
    public void getPacienteById() throws Exception {
        PacienteModel paciente = new PacienteModel("Juan", "Perez", "12345678", LocalDate.now(), new DomicilioModel("Calle 1", 567, "Provincia", "CP"));
        paciente = pacienteService.create(paciente);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/" + paciente.getPacienteID())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paciente encontrado"));
    }

    @Test
    public void createPaciente() throws Exception {
        PacienteModel paciente = new PacienteModel("Carlos", "Gomez", "87654321", LocalDate.now(), new DomicilioModel("Calle 2", 456, "Provincia", "CP"));
        String jsonRequest = objectMapper.writeValueAsString(paciente);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paciente creado"));
    }

    @Test
    public void updatePaciente() throws Exception {
        PacienteModel paciente = new PacienteModel("Ana", "Lopez", "11223344", LocalDate.now(), new DomicilioModel("Calle 3", 765, "Provincia", "CP"));
        paciente = pacienteService.create(paciente);
        paciente.setNombre("Ana Maria");
        String jsonRequest = objectMapper.writeValueAsString(paciente);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/pacientes/" + paciente.getPacienteID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paciente actualizado correctamente"));
    }

    @Test
    public void deletePaciente() throws Exception {
        PacienteModel paciente = new PacienteModel("Luis", "Garcia", "55667788", LocalDate.now(), new DomicilioModel("Calle 4", 456, "Provincia", "CP"));
        paciente = pacienteService.create(paciente);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/" + paciente.getPacienteID())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paciente eliminado correctamente"));
    }

    @Test
    public void getPacienteByDni() throws Exception {
        PacienteModel paciente = new PacienteModel("Marcos", "Martinez", "99887766", LocalDate.now(), new DomicilioModel("Calle 5", 567, "Provincia", "CP"));
        paciente = pacienteService.create(paciente);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/dni/" + paciente.getDni())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paciente encontrado"));
    }

    @Test
    public void getCountPacientes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/cantidad")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Cantidad de Pacientes"));
    }

    @Test
    public void getPacientesByName() throws Exception {
        PacienteModel paciente = new PacienteModel("Laura", "Gomez", "33445566", LocalDate.now(), new DomicilioModel("Calle 6", 987, "Provincia", "CP"));
        paciente = pacienteService.create(paciente);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/busqueda/Laura")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Pacientes encontrados por nombre"));
    }


}
