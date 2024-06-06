package com.backend.clinica.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "odontologo")
public class OdontologoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odontologoID;
    @Column(unique = true, nullable = false)
    private String numeroMatricula;
    @Column
    private String nombre;
    @Column
    private String apellido;

    public OdontologoModel(Long odontologoID, String numeroMatricula, String nombre, String apellido) {
        this.odontologoID = odontologoID;
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public OdontologoModel(String numeroMatricula, String nombre, String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public OdontologoModel() {
    }

    @Override
    public String toString() {
        return "Odontologo[" +
                "odontologoID=" + odontologoID +
                ", numeroMatricula='" + numeroMatricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ']';
    }

    public void setOdontologoID(Long odontologoID) {
        this.odontologoID = odontologoID;
    }

    public Long getOdontologoID() {
        return odontologoID;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
