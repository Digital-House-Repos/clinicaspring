package com.backend.clinica.repository;

import com.backend.clinica.entity.OdontologoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<OdontologoModel, Long> {
}
