package com.backend.clinica.repository;

import com.backend.clinica.entity.OdontologoModel;
import com.backend.clinica.entity.PacienteModel;
import com.backend.clinica.entity.TurnoModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TurnoDAO implements IDAO<TurnoModel> {
    private static final Logger logger = Logger.getLogger(TurnoDAO.class);
    private final ArrayList<TurnoModel> turnosDB = new ArrayList<>();
    private static Integer turnoID = 0;

    @Override
    public TurnoModel create(TurnoModel turnoModel) {
        turnoModel.setTurnoID(++turnoID);
        PacienteModel paciente = new PacienteDAO().findById(turnoModel.getPaciente().getPacienteID());
        OdontologoModel odontologo = new OdontologoDAO().findById(turnoModel.getOdontologo().getOdontologoID());

        if (paciente != null && odontologo != null) {
            turnoModel.setPaciente(paciente);
            turnoModel.setOdontologo(odontologo);
            turnosDB.add(turnoModel);
            logger.info("POST - Turno creado correctamente con ID " + turnoModel.getTurnoID());
            return turnoModel;
        }
        logger.error("POST - Error al crear el Turno");
        return null;
    }

    @Override
    public TurnoModel findById(int id) {
        for (TurnoModel turno : turnosDB) {
            if (turno.getTurnoID().equals(id)) {
                logger.info("GET - Turno con ID " + id + " obtenido correctamente");
                return turno;
            }
        }
        logger.error("GET - Error al obtener el Turno con ID " + id);
        return null;
    }

    @Override
    public ArrayList<TurnoModel> findAll() {
        logger.info("GET - Turnos obtenidos correctamente");
        return turnosDB;
    }

    @Override
    public TurnoModel update(TurnoModel turnoModel, Integer id) {
        for (TurnoModel turno : turnosDB) {
            if (turno.getTurnoID().equals(id)) {
                PacienteModel paciente = new PacienteDAO().findById(turnoModel.getPaciente().getPacienteID());
                OdontologoModel odontologo = new OdontologoDAO().findById(turnoModel.getOdontologo().getOdontologoID());
                if (paciente == null || odontologo == null) {
                    logger.error("PUT - Error al actualizar el Turno con ID " + id);
                    return null;
                }

                turnoModel.setTurnoID(turno.getTurnoID());
                turnoModel.setPaciente(paciente);
                turnoModel.setOdontologo(odontologo);
                turnosDB.set(turnosDB.indexOf(turno), turnoModel);
                logger.info("PUT - Turno con ID " + id + " actualizado correctamente");
                return turnoModel;
            }
        }
        logger.error("PUT - Error al actualizar el Turno con ID " + id);
        return null;
    }

    @Override
    public boolean delete(int id) {
        for (TurnoModel turno : turnosDB) {
            if (turno.getTurnoID().equals(id)) {
                turnosDB.remove(turno);
                logger.info("DELETE - Turno con ID " + id + " eliminado correctamente");
                return true;
            }
        }
        logger.error("DELETE - Error al eliminar el Turno con ID " + id);
        return false;
    }
}
