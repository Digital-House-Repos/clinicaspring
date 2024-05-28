package com.backend.clinica.controller;

import com.backend.clinica.models.PacienteModel;
import com.backend.clinica.service.IService;
import com.backend.clinica.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final IService<PacienteModel> pacienteService;

    public PacienteController() {
        this.pacienteService = new PacienteService();
    }

    @GetMapping
    public ArrayList<PacienteModel> getPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public PacienteModel getPaciente(@PathVariable("id") Integer id) {
        return pacienteService.findById(id);
    }

    @PostMapping
    public PacienteModel createPaciente(@RequestBody PacienteModel pacienteModel) {
        return pacienteService.create(pacienteModel);
    }
}
