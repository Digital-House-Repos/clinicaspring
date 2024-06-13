package com.backend.clinica.service;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.exception.BadRequestException;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import com.backend.clinica.exception.ResourceNotFoundException;
import com.backend.clinica.repository.OdontologoRepository;
import com.backend.clinica.repository.PacienteRepository;
import com.backend.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;

    public List<TurnoModel> findAll() {
        return turnoRepository.findAll();
    }

    public TurnoModel findById(Long id) throws ResourceNotFoundException {
        Optional<TurnoModel> turno = turnoRepository.findById(id);

        if (turno.isPresent()) {
            return turno.get();
        } else {
            throw new ResourceNotFoundException("Turno", "id", id);
        }
    }

    public TurnoModel create(TurnoModel turnoModel) throws EntityAlreadyExistsException, BadRequestException {
        Optional<PacienteModel> paciente = pacienteRepository.findById(turnoModel.getPaciente().getPacienteID());
        if (paciente.isEmpty()) {
            throw new BadRequestException("Paciente", "id", turnoModel.getPaciente().getPacienteID().toString(), "paciente");
        }

        Optional<OdontologoModel> odontologo = odontologoRepository.findById(turnoModel.getOdontologo().getOdontologoID());
        if (odontologo.isEmpty()) {
            throw new BadRequestException("Odontologo", "id", turnoModel.getOdontologo().getOdontologoID().toString(), "odontologo");
        }

        turnoModel.setOdontologo(odontologo.get());
        turnoModel.setPaciente(paciente.get());
        return turnoRepository.save(turnoModel);
    }

    public TurnoModel update(TurnoModel turnoModel) throws ResourceNotFoundException, EntityAlreadyExistsException {
        Optional<PacienteModel> paciente = pacienteRepository.findById(turnoModel.getPaciente().getPacienteID());
        if (paciente.isEmpty()) {
            throw new ResourceNotFoundException("Paciente", "id", turnoModel.getPaciente().getPacienteID());
        }

        Optional<OdontologoModel> odontologo = odontologoRepository.findById(turnoModel.getOdontologo().getOdontologoID());
        if (odontologo.isEmpty()) {
            throw new ResourceNotFoundException("Odontologo", "id", turnoModel.getOdontologo().getOdontologoID());
        }

        turnoModel.setOdontologo(odontologo.get());
        turnoModel.setPaciente(paciente.get());
        return turnoRepository.save(turnoModel);
    }

    public TurnoModel delete(Long id) throws ResourceNotFoundException {
        Optional<TurnoModel> turno = turnoRepository.findById(id);

        if (turno.isPresent()) {
            turnoRepository.deleteById(id);
            return turno.get();
        } else {
            throw new ResourceNotFoundException("Turno", "id", id);
        }
    }

    public Long countAll() {
        return turnoRepository.countTurnos();
    }
}
