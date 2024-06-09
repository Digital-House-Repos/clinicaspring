package com.backend.clinica.controller;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<CustomResponse> getOdontologos() {
        List<OdontologoModel> odontologos = odontologoService.findAll();
        if (odontologos.isEmpty()) {
            CustomResponse cr = new CustomResponse(false, "No hay odontologos en la base de datos", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Odontologos encontrados", odontologos);
            return ResponseEntity.status(200).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getOdontologo(@PathVariable("id") Long id) {
        Optional<OdontologoModel> odontologo = odontologoService.findById(id);

        if (odontologo.isPresent()) {
            CustomResponse cr = new CustomResponse(true, "Odontologo encontrado", odontologo.get());
            return ResponseEntity.status(302).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Odontologo no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> createOdontologo(@RequestBody OdontologoModel odontologoModel) {
        Optional<OdontologoModel> odontologoEqualsByMatricula = odontologoService.findByMatricula(odontologoModel.getNumeroMatricula());
        if (odontologoEqualsByMatricula.isPresent()) {
            CustomResponse cr = new CustomResponse(false, "Ya existe un odontologo con esa matricula", null);
            return ResponseEntity.status(400).body(cr);
        }

        OdontologoModel odontologo = odontologoService.create(odontologoModel);

        if (odontologo == null) {
            CustomResponse cr = new CustomResponse(false, "Error al crear el odontologo", null);
            return ResponseEntity.status(400).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Odontologo creado correctamente", odontologo);
            return ResponseEntity.status(201).body(cr);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateOdontologo(@RequestBody OdontologoModel odontologoModel, @PathVariable("id") Long id) {
        Optional<OdontologoModel> odontologoEqualsByMatricula = odontologoService.findByMatricula(odontologoModel.getNumeroMatricula());
        if (odontologoEqualsByMatricula.isPresent() &&
                odontologoModel.getNumeroMatricula().equals(odontologoEqualsByMatricula.get().getNumeroMatricula()) &&
                odontologoEqualsByMatricula.get().getOdontologoID() != id) {
            CustomResponse cr = new CustomResponse(false, "Ya existe un odontologo con esa matricula", null);
            return ResponseEntity.status(400).body(cr);
        }

        Optional<OdontologoModel> odontologo = odontologoService.findById(id);

        if (odontologo.isPresent()) {
            odontologoModel.setOdontologoID(id);
            odontologoService.update(odontologoModel);
            CustomResponse cr = new CustomResponse(true, "Odontologo actualizado correctamente", odontologoModel);
            return ResponseEntity.status(200).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Odontologo no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteOdontologo(@PathVariable("id") Long id) {
        Optional<OdontologoModel> odontologo = odontologoService.findById(id);

        if (odontologo.isPresent()) {
            odontologoService.delete(id);
            CustomResponse cr = new CustomResponse(true, "Odontologo eliminado correctamente", odontologo.get());
            return ResponseEntity.status(200).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Odontologo no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<CustomResponse> getOdontologoByMatricula(@PathVariable("matricula") String matricula) {
        Optional<OdontologoModel> odontologo = odontologoService.findByMatricula(matricula);

        if (odontologo.isPresent()) {
            CustomResponse cr = new CustomResponse(true, "Odontologo encontrado", odontologo.get());
            return ResponseEntity.status(302).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(false, "Odontologo no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        }
    }
}
