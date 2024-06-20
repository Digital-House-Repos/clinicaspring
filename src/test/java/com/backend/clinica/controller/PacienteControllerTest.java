package com.backend.clinica.controller;

import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import com.backend.clinica.exception.ResourceNotFoundException;
import com.backend.clinica.repository.PacienteRepository;
import com.backend.clinica.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PacienteControllerTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<PacienteModel> pacientes = Arrays.asList(new PacienteModel(), new PacienteModel());
        when(pacienteRepository.findAll()).thenReturn(pacientes);

        List<PacienteModel> resultado = pacienteService.findAll();

        assertEquals(pacientes, resultado);
        verify(pacienteRepository).findAll();
    }

    @Test
    public void testFindById_ExistingPaciente() throws ResourceNotFoundException {
        PacienteModel paciente = new PacienteModel();
        paciente.setPacienteID(1L);
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        PacienteModel resultado = pacienteService.findById(1L);

        assertEquals(paciente, resultado);
        verify(pacienteRepository).findById(1L);
    }

    @Test
    public void testFindById_NonexistentPaciente() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pacienteService.findById(1L));
        verify(pacienteRepository).findById(1L);
    }

    @Test
    public void testCreate_NewPaciente() throws EntityAlreadyExistsException {
        // Crear un objeto PacienteModel y establecer sus atributos correctamente
        PacienteModel paciente = new PacienteModel();
        paciente.setDni("12345678");
        paciente.setNombre("Juan");
        paciente.setApellido("Pérez");
        // ... (establecer otros atributos relevantes)

        // Configurar el mock del repositorio para simular que no existe un paciente con el mismo DNI
        when(pacienteRepository.findByDni("12345678")).thenReturn(Optional.empty());
        // Configurar el mock para devolver el mismo objeto al guardar (simulando éxito en la persistencia)
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        // Llamar al método create del servicio
        PacienteModel resultado = pacienteService.create(paciente);

        // Verificar que el resultado es el mismo objeto PacienteModel que se creó
        assertEquals(paciente, resultado);
        // Verificar que se llamó al método findByDni con el DNI correcto
        verify(pacienteRepository).findByDni("12345678");
        // Verificar que se llamó al método save con el objeto PacienteModel
        verify(pacienteRepository).save(paciente);
    }

    @Test
    public void testCreate_ExistingPaciente() {
        // Crear un objeto PacienteModel y establecer sus atributos (incluyendo el DNI que ya existe)
        PacienteModel pacienteExistente = new PacienteModel();
        pacienteExistente.setPacienteID(1L); // Asignar un ID para simular un paciente existente
        pacienteExistente.setDni("12345670");
        pacienteExistente.setNombre("Juan");
        pacienteExistente.setApellido("Pérez");
        // ... (establecer otros atributos relevantes)

        // Crear un nuevo PacienteModel con el mismo DNI que el paciente existente
        PacienteModel nuevoPaciente = new PacienteModel();
        nuevoPaciente.setDni("12345670");

        // Configurar el mock del repositorio para devolver el paciente existente al buscar por DNI
        when(pacienteRepository.findByDni("12345670")).thenReturn(Optional.of(pacienteExistente));

        // Verificar que se lanza la excepción EntityAlreadyExistsException al intentar crear un nuevo paciente
        assertThrows(EntityAlreadyExistsException.class, () -> pacienteService.create(nuevoPaciente));

        // Verificar que se llamó al método findByDni con el DNI correcto
        verify(pacienteRepository).findByDni("12345670");
        // Verificar que NUNCA se llamó al método save (ya que no debería crearse el paciente)
        verify(pacienteRepository, never()).save(any(PacienteModel.class));
    }

    @Test
    public void testUpdate_ExistingPaciente() throws ResourceNotFoundException, EntityAlreadyExistsException {
        // Crear un objeto PacienteModel existente para simular la actualización
        PacienteModel pacienteExistente = new PacienteModel();
        pacienteExistente.setPacienteID(1L);
        pacienteExistente.setDni("12345678");
        pacienteExistente.setNombre("Juan");
        pacienteExistente.setApellido("Pérez");

        // Crear un objeto PacienteModel con los datos actualizados
        PacienteModel pacienteActualizado = new PacienteModel();
        pacienteActualizado.setPacienteID(1L); // Mismo ID para indicar actualización
        pacienteActualizado.setDni("12345678"); // Mismo DNI, no debería causar conflicto
        pacienteActualizado.setNombre("Juan Carlos"); // Nombre actualizado
        pacienteActualizado.setApellido("Pérez Gómez"); // Apellido actualizado

        // Configurar el mock del repositorio
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteExistente));
        when(pacienteRepository.findByDni("12345678")).thenReturn(Optional.of(pacienteExistente)); // Simular que el DNI no está siendo usado por otro paciente
        when(pacienteRepository.save(pacienteActualizado)).thenReturn(pacienteActualizado);

        // Llamar al método update del servicio
        PacienteModel resultado = pacienteService.update(pacienteActualizado);

        // Verificar que el resultado es el objeto actualizado
        assertEquals(pacienteActualizado, resultado);
        verify(pacienteRepository).findById(1L);
        verify(pacienteRepository).findByDni("12345678");
        verify(pacienteRepository).save(pacienteActualizado);
    }
}