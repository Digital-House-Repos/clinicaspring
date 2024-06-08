package com.backend.clinica.service;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Optional<OdontologoModel> findById(Long id) {
        return odontologoRepository.findById(id);
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
    public void delete(Long id) {
        odontologoRepository.deleteById(id);
    }

    public Optional<OdontologoModel> findByMatricula(String matricula) {
        return odontologoRepository.findByNumeroMatricula(matricula);
    }
}
