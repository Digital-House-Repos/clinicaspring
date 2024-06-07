package com.backend.clinica.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "domicilio")
public class DomicilioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long domicilioID;
    @Column
    private String calle;
    @Column
    private Integer numero;
    @Column
    private String localidad;
    @Column
    private String provincia;

    public DomicilioModel(String calle, Integer numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public DomicilioModel(Long domicilioID, String calle, Integer numero, String localidad, String provincia) {
        this.domicilioID = domicilioID;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public DomicilioModel() {
    }

    @Override
    public String toString() {
        return "Domicilio [" +
                "domicilioID=" + domicilioID +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ']';
    }

    public void setDomicilioID(Long domicilioID) {
        this.domicilioID = domicilioID;
    }

    public Long getDomicilioID() {
        return domicilioID;
    }

    public String getCalle() {
        return calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }
}
