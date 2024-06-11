package com.backend.clinica.service;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import com.backend.clinica.exception.EntityNotFoundException;
import com.backend.clinica.repository.OdontologoRepository;
import com.backend.clinica.repository.PacienteRepository;
import com.backend.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements IService<TurnoModel> {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    public List<TurnoModel> findAll() {
        return turnoRepository.findAll();
    }

    @Override
    public TurnoModel findById(Long id) throws EntityNotFoundException {
        Optional<TurnoModel> turno = turnoRepository.findById(id);

        if (turno.isPresent()) {
            return turno.get();
        } else {
            throw new EntityNotFoundException("Turno", "id", id);
        }
    }

    @Override
    public TurnoModel create(TurnoModel turnoModel) throws EntityNotFoundException, EntityAlreadyExistsException {
        Optional<PacienteModel> paciente = pacienteRepository.findById(turnoModel.getPaciente().getPacienteID());
        if (paciente.isEmpty()) {
            throw new EntityNotFoundException("Paciente", "id", turnoModel.getPaciente().getPacienteID());
        }

        Optional<OdontologoModel> odontologo = odontologoRepository.findById(turnoModel.getOdontologo().getOdontologoID());
        if (odontologo.isEmpty()) {
            throw new EntityNotFoundException("Odontologo", "id", turnoModel.getOdontologo().getOdontologoID());
        }

        turnoModel.setOdontologo(odontologo.get());
        turnoModel.setPaciente(paciente.get());
        return turnoRepository.save(turnoModel);
    }

    @Override
    public TurnoModel update(TurnoModel turnoModel) throws EntityNotFoundException, EntityAlreadyExistsException {
        Optional<PacienteModel> paciente = pacienteRepository.findById(turnoModel.getPaciente().getPacienteID());
        if (paciente.isEmpty()) {
            throw new EntityNotFoundException("Paciente", "id", turnoModel.getPaciente().getPacienteID());
        }

        Optional<OdontologoModel> odontologo = odontologoRepository.findById(turnoModel.getOdontologo().getOdontologoID());
        if (odontologo.isEmpty()) {
            throw new EntityNotFoundException("Odontologo", "id", turnoModel.getOdontologo().getOdontologoID());
        }

        turnoModel.setOdontologo(odontologo.get());
        turnoModel.setPaciente(paciente.get());
        return turnoRepository.save(turnoModel);
    }

    @Override
    public TurnoModel delete(Long id) throws EntityNotFoundException {
        Optional<TurnoModel> turno = turnoRepository.findById(id);

        if (turno.isPresent()) {
            turnoRepository.deleteById(id);
            return turno.get();
        } else {
            throw new EntityNotFoundException("Turno", "id", id);
        }
    }

    @Override
    public Long countAll() {
        return turnoRepository.countTurnos();
    }
}
