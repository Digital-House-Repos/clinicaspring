package com.backend.clinica.controller;

import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.exception.BadRequestException;
import com.backend.clinica.exception.ResourceNotFoundException;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import com.backend.clinica.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger logger = Logger.getLogger(TurnoController.class);

    @Autowired
    private TurnoService turnoService;

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
            logger.error("Error en la DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getTurno(@PathVariable("id") Long id) {
        try {
            TurnoModel turno = turnoService.findById(id);
            CustomResponse cr = new CustomResponse(true, "Turno encontrado", turno);
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
    public ResponseEntity<CustomResponse> createTurno(@RequestBody TurnoModel turnoModel) {
        try {
            TurnoModel turno = turnoService.create(turnoModel);
            CustomResponse cr = new CustomResponse(true, "Turno creado correctamente", turno);
            return ResponseEntity.status(200).body(cr);

        } catch (BadRequestException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Bad request");
            logger.error("Bad request: " + e.getMessage());
            return ResponseEntity.status(400).body(cr);
        } catch (EntityAlreadyExistsException e) {
            CustomResponse cr = new CustomResponse(false, e.getMessage(), "Entity already exists");
            logger.error("Entity already exists: " + e.getMessage());
            return ResponseEntity.status(400).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(true, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
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
    public ResponseEntity<CustomResponse> deleteTurno(@PathVariable("id") Long id) {
        try {
            TurnoModel turno = turnoService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Turno eliminado correctamente", turno);
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
    public ResponseEntity<CustomResponse> getCountTurnos() {
        try {
            Long count = turnoService.countAll();
            CustomResponse cr = new CustomResponse(true, "Cantidad de Turnos", count);
            return ResponseEntity.status(200).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }
}