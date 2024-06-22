package com.backend.clinica.controller;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import com.backend.clinica.exception.ResourceNotFoundException;
import com.backend.clinica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<CustomResponse> getOdontologos() {
        try {
            List<OdontologoModel> odontologos = odontologoService.findAll();
            if (odontologos.isEmpty()) {
                CustomResponse cr = new CustomResponse(true, "No se encontraron odontólogos", "Empty list");
                return ResponseEntity.status(404).body(cr);
            } else {
                CustomResponse cr = new CustomResponse(true, "Odontólogos encontrados", odontologos);
                return ResponseEntity.status(200).body(cr);
            }
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getOdontologo(@PathVariable("id") Long id) {
        try {
            OdontologoModel odontologo = odontologoService.findById(id);
            CustomResponse cr = new CustomResponse(true, "Odontologo encontrado", odontologo);
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
    public ResponseEntity<CustomResponse> createOdontologo(@RequestBody OdontologoModel odontologoModel) {
        try {
            OdontologoModel odontologo = odontologoService.create(odontologoModel);
            CustomResponse cr = new CustomResponse(true, "Odontologo creado correctamente", odontologo);
            return ResponseEntity.status(201).body(cr);

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
    public ResponseEntity<CustomResponse> updateOdontologo(@RequestBody OdontologoModel odontologoModel, @PathVariable("id") Long id) {
        try {
            odontologoModel.setOdontologoID(id);
            OdontologoModel odontologo = odontologoService.update(odontologoModel);
            CustomResponse cr = new CustomResponse(true, "Odontologo actualizado correctamente", odontologo);
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
    public ResponseEntity<CustomResponse> deleteOdontologo(@PathVariable("id") Long id) {
        try {
            OdontologoModel odontologo = odontologoService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Odontologo eliminado correctamente", odontologo);
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

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<CustomResponse> getOdontologoByMatricula(@PathVariable("matricula") String matricula) {
        try {
            OdontologoModel odontologo = odontologoService.findByMatricula(matricula);
            CustomResponse cr = new CustomResponse(true, "Odontologo encontrado", odontologo);
            return ResponseEntity.status(302).body(cr);

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
    public ResponseEntity<CustomResponse> getCountOdontologos() {
        try {
            Long cantidad = odontologoService.countAll();
            CustomResponse cr = new CustomResponse(true, "Cantidad de odontólogos", cantidad);
            return ResponseEntity.status(200).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/busqueda/{nombre}")
    public ResponseEntity<CustomResponse> getOdontologoByName(@PathVariable("nombre") String nombre) {
        try {
            List<OdontologoModel> lista = odontologoService.findByNameRegEx(nombre);
            if (lista.isEmpty()) {
                CustomResponse cr = new CustomResponse(true, "No se encontraron odontólogos", "Empty list");
                return ResponseEntity.status(404).body(cr);
            } else {
                CustomResponse cr = new CustomResponse(true, "Odontólogos encontrados por nombre", lista);
                return ResponseEntity.status(200).body(cr);
            }
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            logger.error("Error en DB: " + e.getMessage());
            return ResponseEntity.status(500).body(cr);
        }
    }
}
