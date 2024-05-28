package com.backend.clinica.controller;

import com.backend.clinica.models.OdontologoModel;
import com.backend.clinica.service.IService;
import com.backend.clinica.service.OdontologoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final IService<OdontologoModel> odontologoService;

    public OdontologoController() {
        this.odontologoService = new OdontologoService();
    }

    @GetMapping("/")
    public ArrayList<OdontologoModel> getOdontologos() {
        return odontologoService.findAll();
    }
}