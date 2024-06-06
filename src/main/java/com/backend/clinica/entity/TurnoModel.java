package com.backend.clinica.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turno")
public class TurnoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long turnoID;
    @ManyToOne
    @JoinColumn(name = "pacienteID", nullable = false)
    private PacienteModel paciente;
    @ManyToOne
    @JoinColumn(name = "odontologoID", nullable = false)
    private OdontologoModel odontologo;
    @Column
    private LocalDateTime fechaHora;

    public TurnoModel(Long turnoID, PacienteModel paciente, OdontologoModel odontologo, LocalDateTime fechaHora) {
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

    public void setTurnoID(Long turnoID) {
        this.turnoID = turnoID;
    }

    public Long getTurnoID() {
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
