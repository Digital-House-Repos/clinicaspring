package com.backend.clinica.controller;

import com.backend.clinica.entity.OdontologoModel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OdontologoControllerTest {
    @Autowired
    private OdontologoController odontologoController;

    @Test
    public void testGetOdontologos() {
        String expectedMessage = "Odontólogos encontrados";
        String actualMessage = odontologoController.getOdontologos().getBody().getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testGetOdontologo() {
        String expectedMessage = "Odontologo encontrado";
        String actualMessage = odontologoController.getOdontologo(1L).getBody().getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testCreateOdontologo() {
        OdontologoModel o1 = new OdontologoModel("999", "John", "Doe");
        Boolean expectedMessage = true;
        Boolean actualMessage = odontologoController.createOdontologo(o1).getBody().getOk();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}


