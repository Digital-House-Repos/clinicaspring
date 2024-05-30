package com.backend.clinica.controller;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.service.IService;
import com.backend.clinica.service.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final IService<OdontologoModel> odontologoService;

    public OdontologoController() {
        this.odontologoService = new OdontologoService();
    }

    @GetMapping
    public ArrayList<OdontologoModel> getOdontologos() {
        return odontologoService.findAll();
    }

    @GetMapping("/{id}")
    public OdontologoModel getOdontologo(@PathVariable("id") Integer id) {
        return odontologoService.findById(id);
    }

    @PostMapping
    public OdontologoModel createOdontologo(@RequestBody OdontologoModel odontologoModel) {
        return odontologoService.create(odontologoModel);
    }

    @PutMapping("/{id}")
    public OdontologoModel updateOdontologo(@RequestBody OdontologoModel odontologoModel, @PathVariable("id") Integer id) {
        return odontologoService.update(odontologoModel, id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOdontologo(@PathVariable("id") Integer id) {
        return odontologoService.delete(id);
    }
}
