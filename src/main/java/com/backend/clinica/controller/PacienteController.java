package com.backend.clinica.controller;

import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.service.PacienteService;
import com.backend.clinica.exception.ResourceNotFoundException;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<CustomResponse> getPacientes() {
        try {
            List<PacienteModel> pacientes = pacienteService.findAll();
            if (pacientes.isEmpty()) {
                CustomResponse cr = new CustomResponse(true, "No se encontraron pacientes", "Empty list");
                return ResponseEntity.status(404).body(cr);
            } else {
                CustomResponse cr = new CustomResponse(true, "Pacientes encontrados", pacientes);
                return ResponseEntity.status(200).body(cr);
            }
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getPaciente(@PathVariable("id") Long id) {
        try {
            PacienteModel paciente = pacienteService.findById(id);
            CustomResponse cr = new CustomResponse(true, "Paciente encontrado", paciente);
            return ResponseEntity.status(200).body(cr);

        } catch (ResourceNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
            logger.error("Entity not found: " + e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> createPaciente(@RequestBody PacienteModel pacienteModel) {
        try {
            PacienteModel paciente = pacienteService.create(pacienteModel);
            CustomResponse cr = new CustomResponse(true, "Paciente creado", paciente);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityAlreadyExistsException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity already exists");
            logger.error("Entity already exists: " + e.getMessage());
            return ResponseEntity.status(400).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updatePaciente(@RequestBody PacienteModel pacienteModel, @PathVariable("id") Long id) {
        try {
            pacienteModel.setPacienteID(id);
            PacienteModel paciente = pacienteService.update(pacienteModel);
            CustomResponse cr = new CustomResponse(true, "Paciente actualizado correctamente", paciente);
            return ResponseEntity.status(200).body(cr);

        } catch (ResourceNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
            logger.error("Entity not found: " + e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (EntityAlreadyExistsException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity already exists");
            logger.error("Entity already exists: " + e.getMessage());
            return ResponseEntity.status(400).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deletePaciente(@PathVariable("id") Long id) {
        try {
            PacienteModel paciente = pacienteService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Paciente eliminado correctamente", paciente);
            return ResponseEntity.status(200).body(cr);

        } catch (ResourceNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
            logger.error("Entity not found: " + e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<CustomResponse> getPacienteByDni(@PathVariable("dni") String dni) {
        try {
            PacienteModel paciente = pacienteService.findByDni(dni);
            CustomResponse cr = new CustomResponse(true, "Paciente encontrado", paciente);
            return ResponseEntity.status(200).body(cr);

        } catch (ResourceNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
            logger.error("Entity not found: " + e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/cantidad")
    public ResponseEntity<CustomResponse> getCountPacientes() {
        try {
            Long count = pacienteService.countAll();
            CustomResponse cr = new CustomResponse(true, "Cantidad de Pacientes", count);
            return ResponseEntity.status(200).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/busqueda/{nombre}")
    public ResponseEntity<CustomResponse> getByName(@PathVariable("nombre") String nombre) {
        try {
            List<PacienteModel> lista = pacienteService.findByNameRegEx(nombre);
            if (lista.isEmpty()) {
                CustomResponse cr = new CustomResponse(true, "No se encontraron pacientes", "Empty list");
                return ResponseEntity.status(404).body(cr);
            } else {
                CustomResponse cr = new CustomResponse(true, "Pacientes encontrados por nombre", lista);
                return ResponseEntity.status(200).body(cr);
            }
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }
}
