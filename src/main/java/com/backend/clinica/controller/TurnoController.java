package com.backend.clinica.controller;

import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private IService<TurnoModel> turnoService;


    @GetMapping
    public ResponseEntity<CustomResponse> getTurnos() {
        ArrayList<TurnoModel> turnos = turnoService.findAll();
        if (turnos.isEmpty()) {
            CustomResponse cr = new CustomResponse(false, "No hay turnos en la base de datos", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Turnos encontrados", turnos);
            return ResponseEntity.status(200).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getTurno(@PathVariable("id") Integer id) {
        TurnoModel turno = turnoService.findById(id);

        if (turno == null) {
            CustomResponse cr = new CustomResponse(false, "Turno no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Turno encontrado", turno);
            return ResponseEntity.status(302).body(cr);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> createTurno(@RequestBody TurnoModel turnoModel) {
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
    public ResponseEntity<CustomResponse> updateTurno(@RequestBody TurnoModel turnoModel, @PathVariable("id") Integer id) {
        TurnoModel turno = turnoService.findById(id);

        if (turno == null) {
            CustomResponse cr = new CustomResponse(false, "Turno no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }

        TurnoModel updatedTurno = turnoService.update(turnoModel, id);

        if (updatedTurno == null) {
            CustomResponse cr = new CustomResponse(false, "Error al actualizar el turno", null);
            return ResponseEntity.status(400).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Turno actualizado correctamente", updatedTurno);
            return ResponseEntity.status(200).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteTurno(@PathVariable("id") Integer id) {
        TurnoModel turno = turnoService.findById(id);

        if (turno == null) {
            CustomResponse cr = new CustomResponse(false, "Turno no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }

        boolean deleted = turnoService.delete(id);

        if (!deleted) {
            CustomResponse cr = new CustomResponse(false, "Error al eliminar el turno", null);
            return ResponseEntity.status(400).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Turno eliminado correctamente", null);
            return ResponseEntity.status(200).body(cr);
        }
    }
}
