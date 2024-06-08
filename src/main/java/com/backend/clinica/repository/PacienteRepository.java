package com.backend.clinica.repository;

import com.backend.clinica.entity.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {
    Optional<PacienteModel> findByDni(String dni);
}
