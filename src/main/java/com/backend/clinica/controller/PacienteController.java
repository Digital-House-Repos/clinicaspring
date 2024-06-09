package com.backend.clinica.controller;

import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<CustomResponse> getPacientes() {
        List<PacienteModel> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            CustomResponse cr = new CustomResponse(false, "No hay pacientes en la base de datos", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Pacientes encontrados", pacientes);
            return ResponseEntity.status(200).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getPaciente(@PathVariable("id") Long id) {
        Optional<PacienteModel> paciente = pacienteService.findById(id);

        if (paciente.isPresent()) {
            CustomResponse cr = new CustomResponse(true, "Paciente encontrado", paciente.get());
            return ResponseEntity.status(302).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Paciente no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> createPaciente(@RequestBody PacienteModel pacienteModel) {
        Optional<PacienteModel> pacienteEqualsByDni = pacienteService.findByDni(pacienteModel.getDni());
        if (pacienteEqualsByDni.isPresent()) {
            CustomResponse cr = new CustomResponse(false, "Ya existe un paciente con ese DNI", null);
            return ResponseEntity.status(400).body(cr);
        }

        PacienteModel paciente = pacienteService.create(pacienteModel);

        if (paciente == null) {
            CustomResponse cr = new CustomResponse(false, "Error al crear el paciente", null);
            return ResponseEntity.status(400).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Paciente creado correctamente", paciente);
            return ResponseEntity.status(201).body(cr);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updatePaciente(@RequestBody PacienteModel pacienteModel, @PathVariable("id") Long id) {
        Optional<PacienteModel> pacienteEqualsByDni = pacienteService.findByDni(pacienteModel.getDni());
        if (pacienteEqualsByDni.isPresent() && pacienteModel.getDni().equals(pacienteEqualsByDni.get().getDni())) {
            CustomResponse cr = new CustomResponse(false, "Ya existe un paciente con ese DNI", null);
            return ResponseEntity.status(400).body(cr);
        }

        Optional<PacienteModel> paciente = pacienteService.findById(id);

        if (paciente.isPresent()) {
            pacienteModel.setPacienteID(id);
            pacienteModel.getDomicilio().setDomicilioID(paciente.get().getDomicilio().getDomicilioID());
            pacienteService.update(pacienteModel);
            CustomResponse cr = new CustomResponse(true, "Paciente actualizado correctamente", pacienteModel);
            return ResponseEntity.status(200).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Paciente no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deletePaciente(@PathVariable("id") Long id) {
        Optional<PacienteModel> paciente = pacienteService.findById(id);

        if (paciente.isPresent()) {
            pacienteService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Paciente eliminado correctamente", paciente.get());
            return ResponseEntity.status(200).body(cr);

        } else {
            CustomResponse cr = new CustomResponse(false, "Paciente no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<CustomResponse> getPacienteByDni(@PathVariable("dni") String dni) {
        Optional<PacienteModel> paciente = pacienteService.findByDni(dni);

        if (paciente.isPresent()) {
            CustomResponse cr = new CustomResponse(true, "Paciente encontrado", paciente.get());
            return ResponseEntity.status(302).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Paciente no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }
}
