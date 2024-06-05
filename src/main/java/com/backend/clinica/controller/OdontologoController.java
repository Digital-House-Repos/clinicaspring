package com.backend.clinica.controller;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private IService<OdontologoModel> odontologoService;

    @GetMapping
    public ResponseEntity<CustomResponse> getOdontologos() {
        ArrayList<OdontologoModel> odontologos = odontologoService.findAll();
        if (odontologos.isEmpty()) {
            CustomResponse cr = new CustomResponse(false, "No se encontraron odontólogos", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Odontólogos encontrados", odontologos);
            return ResponseEntity.status(200).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getOdontologo(@PathVariable("id") Integer id) {
        OdontologoModel odontologo = odontologoService.findById(id);

        if (odontologo == null) {
            CustomResponse cr = new CustomResponse(false, "Odontólogo no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Odontólogo encontrado", odontologo);
            return ResponseEntity.status(302).body(cr);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse> createOdontologo(@RequestBody OdontologoModel odontologoModel) {
        OdontologoModel odontologo = odontologoService.create(odontologoModel);

        if (odontologo == null) {
            CustomResponse cr = new CustomResponse(false, "Error al crear el odontólogo", null);
            return ResponseEntity.status(400).body(cr);
        } else {
            CustomResponse cr = new CustomResponse(true, "Odontólogo creado correctamente", odontologo);
            return ResponseEntity.status(201).body(cr);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateOdontologo(@RequestBody OdontologoModel odontologoModel, @PathVariable("id") Integer id) {
        OdontologoModel odontologo = odontologoService.findById(id);

        if (odontologo == null) {
            CustomResponse cr = new CustomResponse(false, "Odontólogo no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            odontologo = odontologoService.update(odontologoModel, id);
            CustomResponse cr = new CustomResponse(true, "Odontólogo actualizado correctamente", odontologo);
            return ResponseEntity.status(200).body(cr);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteOdontologo(@PathVariable("id") Integer id) {
        OdontologoModel odontologo = odontologoService.findById(id);

        if (odontologo == null) {
            CustomResponse cr = new CustomResponse(false, "Odontólogo no encontrado", null);
            return ResponseEntity.status(404).body(cr);
        } else {
            boolean deleted = odontologoService.delete(id);
            if (deleted) {
                CustomResponse cr = new CustomResponse(true, "Odontólogo eliminado correctamente", null);
                return ResponseEntity.status(200).body(cr);
            } else {
                CustomResponse cr = new CustomResponse(false, "Error al eliminar el odontólogo", null);
                return ResponseEntity.status(400).body(cr);
            }
        }
    }
}
