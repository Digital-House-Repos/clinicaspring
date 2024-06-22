package com.backend.clinica.service;

import com.backend.clinica.entity.OdontologoModel;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OdontologoService odontologoService;

    @Test
    public void getOdontologos() throws Exception {
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void getOdontologoById() throws Exception {
        // Crear un odontologo para probar
        OdontologoModel odontologo = odontologoService.create(new OdontologoModel("12345", "Juan", "Perez"));

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/" + odontologo.getOdontologoID())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(respuesta.getResponse().getContentAsString().contains("Juan"));
    }

    @Test
    public void createOdontologo() throws Exception {
        OdontologoModel odontologo = new OdontologoModel("12346", "Carlos", "Gomez");
        String jsonRequest = objectMapper.writeValueAsString(odontologo);

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        assertTrue(respuesta.getResponse().getContentAsString().contains("Carlos"));
    }

    @Test
    public void updateOdontologo() throws Exception {
        // Crear un odontologo para probar
        OdontologoModel odontologo = odontologoService.create(new OdontologoModel("12347", "Ana", "Lopez"));

        // Actualizar el nombre del odontologo
        odontologo.setNombre("Ana Maria");
        String jsonRequest = objectMapper.writeValueAsString(odontologo);

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.put("/odontologos/" + odontologo.getOdontologoID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(respuesta.getResponse().getContentAsString().contains("Ana Maria"));
    }

    @Test
    public void deleteOdontologo() throws Exception {
        // Crear un odontologo para probar
        OdontologoModel odontologo = odontologoService.create(new OdontologoModel("12348", "Luis", "Garcia"));

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/" + odontologo.getOdontologoID())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verificar que se ha eliminado correctamente
        MvcResult buscarRespuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/" + odontologo.getOdontologoID())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        assertFalse(buscarRespuesta.getResponse().getContentAsString().contains("Luis"));
    }

    @Test
    public void getOdontologoByMatricula() throws Exception {
        // Crear un odontologo para probar
        OdontologoModel odontologo = odontologoService.create(new OdontologoModel("12349", "Marcos", "Martinez"));

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/matricula/" + odontologo.getNumeroMatricula())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andReturn();

        assertTrue(respuesta.getResponse().getContentAsString().contains("Marcos"));
    }

    @Test
    public void getOdontologoByName() throws Exception {
        // Crear un odontologo para probar
        OdontologoModel odontologo = odontologoService.create(new OdontologoModel("12350", "Laura", "Gomez"));

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/busqueda/Laura")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(respuesta.getResponse().getContentAsString().contains("Laura"));
    }
}
