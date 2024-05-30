package com.backend.clinica.entity;

import java.time.LocalDateTime;

public class TurnoModel {
    private Integer turnoID;
    private PacienteModel paciente;
    private OdontologoModel odontologo;
    private LocalDateTime fechaHora;

    public TurnoModel(Integer turnoID, PacienteModel paciente, OdontologoModel odontologo, LocalDateTime fechaHora) {
        this.turnoID = turnoID;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHora = fechaHora;
    }

    public TurnoModel(PacienteModel paciente, OdontologoModel odontologo, LocalDateTime fechaHora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHora = fechaHora;
    }

    public TurnoModel() {
    }

    @Override
    public String toString() {
        return "TurnoModel[" +
                "turnoID=" + turnoID +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fechaHora=" + fechaHora +
                ']';
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(OdontologoModel odontologo) {
        this.odontologo = odontologo;
    }

    public void setTurnoID(Integer turnoID) {
        this.turnoID = turnoID;
    }

    public Integer getTurnoID() {
        return turnoID;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public OdontologoModel getOdontologo() {
        return odontologo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}
