package com.backend.clinica.service;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.exception.EntityAlreadyExistsException;
import com.backend.clinica.exception.ResourceNotFoundException;
import com.backend.clinica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OdontologoService implements IService<OdontologoModel> {
    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    public List<OdontologoModel> findAll() {
        return odontologoRepository.findAll();
    }

    @Override
    public OdontologoModel findById(Long id) throws ResourceNotFoundException {
        Optional<OdontologoModel> odontologo = odontologoRepository.findById(id);

        if (odontologo.isPresent()) {
            return odontologo.get();
        } else {
            throw new ResourceNotFoundException("Odontologo", "id", id);
        }
    }

    @Override
    public OdontologoModel create(OdontologoModel odontologoModel) throws EntityAlreadyExistsException {
        Optional<OdontologoModel> odontologoEqualsByMatricula = odontologoRepository.findByNumeroMatricula(odontologoModel.getNumeroMatricula());

        if (odontologoEqualsByMatricula.isPresent()) {
            throw new EntityAlreadyExistsException("Odontologo", "matricula", odontologoModel.getNumeroMatricula());
        } else {
            return odontologoRepository.save(odontologoModel);
        }
    }

    @Override
    public OdontologoModel update(OdontologoModel odontologoModel) throws ResourceNotFoundException, EntityAlreadyExistsException {
        Optional<OdontologoModel> odontologo = odontologoRepository.findById(odontologoModel.getOdontologoID());
        if (odontologo.isEmpty()) {
            throw new ResourceNotFoundException("Odontologo", "id", odontologoModel.getOdontologoID());
        }

        Optional<OdontologoModel> odontologoEqualsByMatricula = odontologoRepository.findByNumeroMatricula(odontologoModel.getNumeroMatricula());
        if (odontologoEqualsByMatricula.isPresent() && !Objects.equals(odontologoEqualsByMatricula.get().getOdontologoID(), odontologoModel.getOdontologoID())) {
            throw new EntityAlreadyExistsException("Odontologo", "matricula", odontologoModel.getNumeroMatricula());
        }

        return odontologoRepository.save(odontologoModel);
    }

    @Override
    public OdontologoModel delete(Long id) throws ResourceNotFoundException {
        Optional<OdontologoModel> odontologo = odontologoRepository.findById(id);

        if (odontologo.isEmpty()) {
            throw new ResourceNotFoundException("Odontologo", "id", id);
        } else {
            odontologoRepository.deleteById(id);
            return odontologo.get();
        }
    }

    public OdontologoModel findByMatricula(String matricula) throws ResourceNotFoundException {
        Optional<OdontologoModel> odontologo = odontologoRepository.findByNumeroMatricula(matricula);

        if (odontologo.isPresent()) {
            return odontologo.get();
        } else {
            throw new ResourceNotFoundException("Odontologo", "matricula", matricula);
        }
    }

    @Override
    public Long countAll() {
        return odontologoRepository.countOdontologos();
    }

    public List<OdontologoModel> findByNameRegEx(String nombre) {
        return odontologoRepository.findByNameRegEx(nombre);
    }
}
