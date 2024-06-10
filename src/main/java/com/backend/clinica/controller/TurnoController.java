package com.backend.clinica.controller;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private IService<TurnoModel> turnoService;
    @Autowired
    private IService<OdontologoModel> odontologoService;
    @Autowired
    private IService<PacienteModel> pacienteService;

    @GetMapping
    public ResponseEntity<CustomResponse> getTurnos() {
        List<TurnoModel> turnos = turnoService.findAll();
        if (turnos.isEmpty()) {
            CustomResponse cr = new CustomResponse(false, "No hay turnos en la base de datos", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Turnos encontrados", turnos);
            return ResponseEntity.status(200).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getTurno(@PathVariable("id") Long id) {
        Optional<TurnoModel> turno = turnoService.findById(id);

        if (turno.isPresent()) {
            CustomResponse cr = new CustomResponse(true, "Turno encontrado", turno.get());
            return ResponseEntity.status(302).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Turno no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> createTurno(@RequestBody TurnoModel turnoModel) {
        Optional<OdontologoModel> odontologo = Optional.ofNullable(odontologoService.findById(turnoModel.getOdontologo().getOdontologoID()));
        Optional<PacienteModel> paciente = Optional.ofNullable(pacienteService.findById(turnoModel.getPaciente().getPacienteID()));

        if (odontologo.isEmpty() || paciente.isEmpty()) {
            CustomResponse cr = new CustomResponse(false, "Error al crear el turno", null);
            return ResponseEntity.status(400).body(cr);
        }

        turnoModel.setOdontologo(odontologo.get());
        turnoModel.setPaciente(paciente.get());
        TurnoModel turno = turnoService.create(turnoModel);

        if (turno == null) {
            CustomResponse cr = new CustomResponse(false, "Error al crear el turno", null);
            return ResponseEntity.status(400).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Turno creado correctamente", turno);
            return ResponseEntity.status(201).body(cr);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateTurno(@RequestBody TurnoModel turnoModel, @PathVariable("id") Long id) {
        Optional<PacienteModel> paciente = Optional.ofNullable(pacienteService.findById(turnoModel.getPaciente().getPacienteID()));
        Optional<OdontologoModel> odontologo = Optional.ofNullable(odontologoService.findById(turnoModel.getOdontologo().getOdontologoID()));

        if (paciente.isEmpty() || odontologo.isEmpty()) {
            CustomResponse cr = new CustomResponse(false, "Error al actualizar el turno", null);
            return ResponseEntity.status(400).body(cr);
        }

        turnoModel.setPaciente(paciente.get());
        turnoModel.setOdontologo(odontologo.get());
        Optional<TurnoModel> turno = turnoService.findById(id);

        if (turno.isPresent()) {
            turnoModel.setTurnoID(id);
            turnoService.update(turnoModel);
            CustomResponse cr = new CustomResponse(true, "Turno actualizado correctamente", turnoModel);
            return ResponseEntity.status(200).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Turno no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteTurno(@PathVariable("id") Long id) {
        Optional<TurnoModel> turno = turnoService.findById(id);

        if (turno.isPresent()) {
            turnoService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Turno eliminado correctamente", turno.get());
            return ResponseEntity.status(200).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Turno no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }
}