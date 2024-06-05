package com.backend.clinica.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PacienteModel {
    private Integer pacienteID;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;
    private DomicilioModel domicilio;

    public PacienteModel(String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioModel domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public PacienteModel(Integer pacienteID, String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioModel domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
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
                ", domicilio=" + domicilio +
                ']';
    }

    public void setPacienteID(Integer pacienteID) {
        this.pacienteID = pacienteID;
    }

    public void setDomicilio(DomicilioModel domicilio) {
        this.domicilio = domicilio;
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

    public DomicilioModel getDomicilio() {
        return domicilio;
    }
}
