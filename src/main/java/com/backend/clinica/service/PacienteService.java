package com.backend.clinica.service;

import com.backend.clinica.repository.PacienteRepository;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import com.backend.clinica.exception.ResourceNotFoundException;
import com.backend.clinica.entity.PacienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public PacienteModel findById(Long id) throws ResourceNotFoundException {
        Optional<PacienteModel> paciente = pacienteRepository.findById(id);

        if (paciente.isPresent()) {
            return paciente.get();
        } else {
            throw new ResourceNotFoundException("Paciente", "id", id);
        }
    }

    @Override
    public PacienteModel create(PacienteModel pacienteModel) throws EntityAlreadyExistsException {
        Optional<PacienteModel> pacienteEqualsByDni = pacienteRepository.findByDni(pacienteModel.getDni());

        if (pacienteEqualsByDni.isPresent()) {
            throw new EntityAlreadyExistsException("Paciente", "dni", pacienteModel.getDni());
        } else {
            return pacienteRepository.save(pacienteModel);
        }
    }

    @Override
    public PacienteModel update(PacienteModel pacienteModel) throws ResourceNotFoundException, EntityAlreadyExistsException {
        Optional<PacienteModel> pacienteFindById = pacienteRepository.findById(pacienteModel.getPacienteID());
        if (pacienteFindById.isEmpty()) {
            throw new ResourceNotFoundException("Paciente", "id", pacienteModel.getPacienteID());
        }

        Optional<PacienteModel> pacienteEqualsByDni = pacienteRepository.findByDni(pacienteModel.getDni());
        if (pacienteEqualsByDni.isPresent() && !Objects.equals(pacienteEqualsByDni.get().getPacienteID(), pacienteModel.getPacienteID())) {
            throw new EntityAlreadyExistsException("Paciente", "dni", pacienteModel.getDni());
        }

        return pacienteRepository.save(pacienteModel);
    }

    @Override
    public PacienteModel delete(Long id) throws ResourceNotFoundException {
        Optional<PacienteModel> paciente = pacienteRepository.findById(id);

        if (paciente.isPresent()) {
            pacienteRepository.deleteById(id);
            return paciente.get();
        } else {
            throw new ResourceNotFoundException("Paciente", "id", id);
        }
    }

    public PacienteModel findByDni(String dni) throws ResourceNotFoundException {
        Optional<PacienteModel> paciente = pacienteRepository.findByDni(dni);

        if (paciente.isPresent()) {
            return paciente.get();
        } else {
            throw new ResourceNotFoundException("Paciente", "dni", dni);
        }
    }

    @Override
    public Long countAll() {
        return pacienteRepository.countPacientes();
    }

    public List<PacienteModel> findByNameRegEx(String name) {
        return pacienteRepository.findByNameRegEx(name);
    }
}
