package com.backend.clinica.service;

import com.backend.clinica.entity.TurnoModel;
import com.backend.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements IService<TurnoModel> {
    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public List<TurnoModel> findAll() {
        return turnoRepository.findAll();
    }

    @Override
    public Optional<TurnoModel> findById(Long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public TurnoModel create(TurnoModel turnoModel) {
        return turnoRepository.save(turnoModel);
    }

    @Override
    public void update(TurnoModel turnoModel) {
        turnoRepository.save(turnoModel);
    }

    @Override
    public void delete(Long id) {
        turnoRepository.deleteById(id);
    }
}
