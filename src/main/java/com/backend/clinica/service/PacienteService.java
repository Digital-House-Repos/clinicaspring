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

    @Override
    public List<PacienteModel> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<PacienteModel> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public PacienteModel create(PacienteModel pacienteModel) {
        return pacienteRepository.save(pacienteModel);
    }

    @Override
    public void update(PacienteModel pacienteModel) {
        pacienteRepository.save(pacienteModel);
    }

    @Override
    public void delete(Long id) {
        pacienteRepository.deleteById(id);
    }
}
