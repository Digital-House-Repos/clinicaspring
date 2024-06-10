package com.backend.clinica.controller;

import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.exception.EntityNotFoundException;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import com.backend.clinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private IService<TurnoModel> turnoService;

    @GetMapping
    public ResponseEntity<CustomResponse> getTurnos() {
        try {
            List<TurnoModel> turnos = turnoService.findAll();
            if (turnos.isEmpty()) {
                CustomResponse cr = new CustomResponse(true, "No se encontraron turnos", "Empty list");
                return ResponseEntity.status(404).body(cr);
            } else {
                CustomResponse cr = new CustomResponse(true, "Turnos encontrados", turnos);
                return ResponseEntity.status(200).body(cr);
            }
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en la DB: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getTurno(@PathVariable("id") Long id) {
        try {
            TurnoModel turno = turnoService.findById(id);
            CustomResponse cr = new CustomResponse(true, "Turno encontrado", turno);
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
    public ResponseEntity<CustomResponse> createTurno(@RequestBody TurnoModel turnoModel) {
        try {
            TurnoModel turno = turnoService.create(turnoModel);
            CustomResponse cr = new CustomResponse(true, "Turno creado correctamente", turno);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(true, e.getMessage(), "Entity not found");
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(true, "Error en DB: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(cr);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateTurno(@RequestBody TurnoModel turnoModel, @PathVariable("id") Long id) {
        try {
            turnoModel.setTurnoID(id);
            TurnoModel turno = turnoService.update(turnoModel);
            CustomResponse cr = new CustomResponse(true, "Turno actualizado correctamente", turno);
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
    public ResponseEntity<CustomResponse> deleteTurno(@PathVariable("id") Long id) {
        try {
            TurnoModel turno = turnoService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Turno eliminado correctamente", turno);
            return ResponseEntity.status(200).body(cr);

        } catch (EntityNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity not found");
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(cr);
        }
    }
}