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
}