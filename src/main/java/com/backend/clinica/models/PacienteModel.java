package com.backend.clinica.models;

import java.time.LocalDate;

public class PacienteModel {
    private Integer pacienteID;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;
    private DomicilioModel domicilioID;

    public PacienteModel(String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioModel domicilioID) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilioID = domicilioID;
    }

    public PacienteModel(Integer pacienteID, String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioModel domicilioID) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilioID = domicilioID;
        this.pacienteID = pacienteID;
    }

    public PacienteModel() {
    }

    @Override
    public String toString() {
        return "Paciente [" +
                "pacienteID=" + pacienteID +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                ", domicilioID=" + domicilioID +
                ']';
    }

    public void setPacienteID(Integer pacienteID) {
        this.pacienteID = pacienteID;
    }

    public void setDomicilioID(DomicilioModel domicilioID) {
        this.domicilioID = domicilioID;
    }

    public Integer getPacienteID() {
        return pacienteID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public DomicilioModel getDomicilioID() {
        return domicilioID;
    }
}
