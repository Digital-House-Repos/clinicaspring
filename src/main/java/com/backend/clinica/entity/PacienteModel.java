package com.backend.clinica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "paciente")
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long pacienteID;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column(unique = true, nullable = false)
    private String dni;
    @Column
    private LocalDate fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilioID", referencedColumnName = "domicilioID")
    private DomicilioModel domicilio;

    public PacienteModel(String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioModel domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public PacienteModel(Long pacienteID, String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioModel domicilio) {
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

}
