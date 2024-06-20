package com.backend.clinica.controller;

import com.backend.clinica.entity.DomicilioModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DomicilioControllerTest {

    @Test
    public void testConstructorVacio() {
        DomicilioModel domicilio = new DomicilioModel();

        assertNull(domicilio.getDomicilioID());
        assertNull(domicilio.getCalle());
        assertNull(domicilio.getNumero());
        assertNull(domicilio.getLocalidad());
        assertNull(domicilio.getProvincia());
    }

    @Test
    public void testConstructorConParametros() {
        DomicilioModel domicilio = new DomicilioModel("Calle Falsa", 123, "Springfield", "Capital Federal");

        assertNull(domicilio.getDomicilioID());
        assertEquals("Calle Falsa", domicilio.getCalle());
        assertEquals(123, domicilio.getNumero());
        assertEquals("Springfield", domicilio.getLocalidad());
        assertEquals("Capital Federal", domicilio.getProvincia());
    }

    @Test
    public void testConstructorConTodosLosParametros() {
        DomicilioModel domicilio = new DomicilioModel(1L, "Calle Falsa", 123, "Springfield", "Capital Federal");

        assertEquals(1L, domicilio.getDomicilioID());
        assertEquals("Calle Falsa", domicilio.getCalle());
        assertEquals(123, domicilio.getNumero());
        assertEquals("Springfield", domicilio.getLocalidad());
        assertEquals("Capital Federal", domicilio.getProvincia());
    }

    @Test
    public void testSettersAndGetters() {
        DomicilioModel domicilio = new DomicilioModel(1L, "Calle Falsa", 123, "Springfield", "Capital Federal");

        assertEquals(1L, domicilio.getDomicilioID());
        assertEquals("Calle Falsa", domicilio.getCalle());
        assertEquals(123, domicilio.getNumero());
        assertEquals("Springfield", domicilio.getLocalidad());
        assertEquals("Capital Federal", domicilio.getProvincia());
    }

    @Test
    public void testToString() {
        DomicilioModel domicilio = new DomicilioModel(1L, "Calle Falsa", 123, "Springfield", "Capital Federal");
        String expected = "Domicilio [domicilioID=1, calle='Calle Falsa', numero=123, localidad='Springfield', provincia='Capital Federal']";

        assertEquals(expected, domicilio.toString());
    }

}
