package tineo.dao;

import org.junit.Assert;
import org.junit.Test;
import tineo.models.DomicilioModel;

import java.util.ArrayList;

public class DomicilioDAOTest {

    @Test
    public void findAllTest() {
        DomicilioDAO domicilioDAO = new DomicilioDAO();
        ArrayList<DomicilioModel> listaDomicilio = domicilioDAO.findAll();
        Assert.assertNotNull(domicilioDAO.findAll());
    }

    @Test
    public void findByIdTest() {
        DomicilioDAO domicilioDAO = new DomicilioDAO();
        DomicilioModel domicilio = domicilioDAO.findById(1);
        Assert.assertNotNull(domicilioDAO.findById(1));
    }

    @Test
    public void findByIdTest2() {
        String messageReturn = "Domicilio [domicilioID=1, calle='Calle Falsa', numero=123, localidad='Springfield', provincia='Springfield']";
        DomicilioDAO domicilioDAO = new DomicilioDAO();
        DomicilioModel domicilio = domicilioDAO.findById(1);
        Assert.assertEquals(messageReturn, domicilio.toString());
    }

    @Test
    public void createTest() {
        DomicilioDAO domicilioDAO = new DomicilioDAO();
        DomicilioModel domicilio = new DomicilioModel(1, "DOMICILIO", 000, "DEMO", "DEMO");
        Assert.assertNotNull(domicilioDAO.create(domicilio));
    }
}