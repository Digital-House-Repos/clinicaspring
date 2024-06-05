package com.backend.clinica.controller;

import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private IService<PacienteModel> pacienteService;

    @GetMapping
    public ResponseEntity<CustomResponse> getPacientes() {
        ArrayList<PacienteModel> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            CustomResponse cr = new CustomResponse(false, "No hay pacientes en la base de datos", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Pacientes encontrados", pacientes);
            return ResponseEntity.status(200).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getPaciente(@PathVariable("id") Integer id) {
        PacienteModel paciente = pacienteService.findById(id);

        if (paciente == null) {
            CustomResponse cr = new CustomResponse(false, "Paciente no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Paciente encontrado", paciente);
            return ResponseEntity.status(302).body(cr);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> createPaciente(@RequestBody PacienteModel pacienteModel) {
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
    public ResponseEntity<CustomResponse> updatePaciente(@RequestBody PacienteModel pacienteModel, @PathVariable("id") Integer id) {
        PacienteModel paciente = pacienteService.findById(id);

        if (paciente == null) {
            CustomResponse cr = new CustomResponse(false, "Paciente no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            pacienteModel.setPacienteID(id);
            PacienteModel pacienteUpdated = pacienteService.update(pacienteModel, id);
            CustomResponse cr = new CustomResponse(true, "Paciente actualizado correctamente", pacienteUpdated);
            return ResponseEntity.status(200).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deletePaciente(@PathVariable("id") Integer id) {
        PacienteModel paciente = pacienteService.findById(id);

        if (paciente == null) {
            CustomResponse cr = new CustomResponse(false, "Paciente no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            pacienteService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Paciente eliminado correctamente", null);
            return ResponseEntity.status(200).body(cr);
        }
    }
}
