package com.backend.clinica.controller;

import com.backend.clinica.models.PacienteModel;
import com.backend.clinica.service.IService;
import com.backend.clinica.service.PacienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final IService<PacienteModel> pacienteService;

    public PacienteController() {
        this.pacienteService = new PacienteService();
    }

    @GetMapping("/")
    public ArrayList<PacienteModel> getPacientes() {
        return pacienteService.findAll();
    }
}
