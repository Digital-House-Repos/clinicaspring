package com.backend.clinica.repository;

import com.backend.clinica.entity.OdontologoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<OdontologoModel, Long> {
    Optional<OdontologoModel> findByNumeroMatricula(String matricula);

    @Query("SELECT COUNT(o) FROM OdontologoModel o")
    Long countOdontologos();
}
