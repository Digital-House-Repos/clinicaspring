package com.backend.clinica.repository;

import com.backend.clinica.entity.OdontologoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<OdontologoModel, Long> {
    Optional<OdontologoModel> findByNumeroMatricula(String matricula);

    @Query("SELECT COUNT(o) FROM OdontologoModel o")
    Long countOdontologos();

    @Query("SELECT o FROM OdontologoModel o WHERE LOWER(CONCAT(o.nombre, ' ', o.apellido)) LIKE LOWER(CONCAT('%', :param, '%'))")
    List<OdontologoModel> findByNameRegEx(@Param("param") String param);
}
