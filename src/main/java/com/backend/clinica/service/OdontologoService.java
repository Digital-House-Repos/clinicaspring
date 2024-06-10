package com.backend.clinica.service;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.exception.EntityNotFoundException;
import com.backend.clinica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IService2<OdontologoModel> {
    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    public List<OdontologoModel> findAll() {
        return odontologoRepository.findAll();
    }

    @Override
    public OdontologoModel findById(Long id) throws EntityNotFoundException {
        Optional<OdontologoModel> odontologo = odontologoRepository.findById(id);

        if (odontologo.isPresent()) {
            return odontologo.get();
        } else {
            throw new EntityNotFoundException("Odontologo", "id", id);
        }
    }

    @Override
    public OdontologoModel create(OdontologoModel odontologoModel) {
        return odontologoRepository.save(odontologoModel);
    }

    @Override
    public void update(OdontologoModel odontologoModel) {
        odontologoRepository.save(odontologoModel);
    }

    @Override
    public OdontologoModel delete(Long id) throws EntityNotFoundException {
        Optional<OdontologoModel> odontologo = odontologoRepository.findById(id);

        if (odontologo.isEmpty()) {
            throw new EntityNotFoundException("Odontologo", "id", id);
        } else {
            odontologoRepository.deleteById(id);
            return odontologo.get();
        }
    }

    public OdontologoModel findByMatricula(String matricula) throws EntityNotFoundException {
        Optional<OdontologoModel> odontologo = odontologoRepository.findByNumeroMatricula(matricula);

        if (odontologo.isPresent()) {
            return odontologo.get();
        } else {
            throw new EntityNotFoundException("Odontologo", "matricula", matricula);
        }
    }
}
