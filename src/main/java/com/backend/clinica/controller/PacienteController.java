package com.backend.clinica.controller;

import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.service.PacienteService;
import com.backend.clinica.exception.EntityNotFoundException;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
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
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getPaciente(@PathVariable("id") Long id) {
        try {
            PacienteModel paciente = pacienteService.findById(id);
            CustomResponse cr = new CustomResponse(true, "Paciente encontrado", paciente);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
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
            return ResponseEntity.status(400).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
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

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
            return ResponseEntity.status(404).body(cr);
        } catch (EntityAlreadyExistsException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity already exists");
            return ResponseEntity.status(400).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deletePaciente(@PathVariable("id") Long id) {
        try {
            PacienteModel paciente = pacienteService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Paciente eliminado correctamente", paciente);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<CustomResponse> getPacienteByDni(@PathVariable("dni") String dni) {
        try {
            PacienteModel paciente = pacienteService.findByDni(dni);
            CustomResponse cr = new CustomResponse(true, "Paciente encontrado", paciente);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
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
            return ResponseEntity.status(500).body(cr);
        }
    }
}
