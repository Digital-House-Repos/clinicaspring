package tineo.dao;

import org.junit.Assert;
import org.junit.Test;
import tineo.models.PacienteModel;

import java.util.ArrayList;

public class PacienteDAOTest {

    @Test
    public void findAllTest() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        ArrayList<PacienteModel> listaPaciente = pacienteDAO.findAll();
        Assert.assertNotNull(pacienteDAO.findAll());
    }

    @Test
    public void findByIdTest() {
        String queryResult = "Paciente [pacienteID=1, nombre='Homero', apellido='Simpson', dni='12345678', fechaIngreso='2021-01-01', domicilioID=Domicilio [domicilioID=1, calle='Calle Falsa', numero=123, localidad='Springfield', provincia='Springfield']]";
        PacienteDAO pacienteDAO = new PacienteDAO();
        PacienteModel paciente = pacienteDAO.findById(1);
        Assert.assertEquals(queryResult, paciente.toString());
    }
}
