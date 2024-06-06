package com.backend.clinica.repository;

import com.backend.clinica.entity.TurnoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<TurnoModel, Long> {
}
