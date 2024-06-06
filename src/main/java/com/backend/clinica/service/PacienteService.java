package com.backend.clinica.service;

import com.backend.clinica.repository.PacienteRepository;
import com.backend.clinica.entity.PacienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IService<PacienteModel> {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<PacienteModel> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<PacienteModel> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    public PacienteModel create(PacienteModel pacienteModel) {
        return pacienteRepository.save(pacienteModel);
    }

    public void update(PacienteModel pacienteModel) {
        pacienteRepository.save(pacienteModel);
    }

    public void delete(Long id) {
        pacienteRepository.deleteById(id);
    }
}
